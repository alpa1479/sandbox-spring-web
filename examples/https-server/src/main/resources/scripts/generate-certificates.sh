# Script should be executed from /scripts folder

CERT_DIR='../certificates'
rm -rf $CERT_DIR

CA_CERT_DIR=$CERT_DIR/ca
SERVER_CERT_DIR=$CERT_DIR/server
CLIENT_CERT_DIR=$CERT_DIR/client

mkdir $CERT_DIR
mkdir $CA_CERT_DIR
mkdir $SERVER_CERT_DIR
mkdir $CLIENT_CERT_DIR

EXPIRATION_TIME=365 # days
PASSWORD='password'

# Creation of CA private key
openssl genrsa -aes256 -passout pass:$PASSWORD -out $CA_CERT_DIR/ca-private-key.key 4096
# Creation of CA CSR using CA private key
openssl req -new -key $CA_CERT_DIR/ca-private-key.key -passin pass:$PASSWORD -subj "//CN=CA" -out $CA_CERT_DIR/ca-certificate-signing-request.csr
# Creation of CA certificate using CA CSR and CA private key
openssl x509 -req -in $CA_CERT_DIR/ca-certificate-signing-request.csr -signkey $CA_CERT_DIR/ca-private-key.key -passin pass:$PASSWORD -days $EXPIRATION_TIME -out $CA_CERT_DIR/ca-self-signed-certificate.pem

# Creation of server private key
openssl genrsa -aes256 -passout pass:$PASSWORD -out $SERVER_CERT_DIR/server-private-key.key 4096
# Creation of server CSR using server private key
openssl req -new -key $SERVER_CERT_DIR/server-private-key.key -passin pass:$PASSWORD -subj "//CN=localhost" -out $SERVER_CERT_DIR/server-certificate-signing-request.csr
# Creation of server certificate using server CSR and server private key with CA certificate
openssl x509 -req -in $SERVER_CERT_DIR/server-certificate-signing-request.csr -CA $CA_CERT_DIR/ca-self-signed-certificate.pem -CAkey $CA_CERT_DIR/ca-private-key.key -passin pass:$PASSWORD -CAcreateserial -days $EXPIRATION_TIME -out $SERVER_CERT_DIR/server-certificate.pem

# Creation of client private key
openssl genrsa -aes256 -passout pass:$PASSWORD -out $CLIENT_CERT_DIR/client-private-key.key 4096
# Creation of client CSR using client private key
openssl req -new -key $CLIENT_CERT_DIR/client-private-key.key -passin pass:$PASSWORD -subj "//CN=client" -out $CLIENT_CERT_DIR/client-certificate-signing-request.csr
# Creation of client certificate using client CSR and client private key with CA certificate
openssl x509 -req -in $CLIENT_CERT_DIR/client-certificate-signing-request.csr -CA $CA_CERT_DIR/ca-self-signed-certificate.pem -CAkey $CA_CERT_DIR/ca-private-key.key -passin pass:$PASSWORD -days $EXPIRATION_TIME -out $CLIENT_CERT_DIR/client-certificate.pem

# Creation of keystore with server certificate and private key
openssl pkcs12 -export -in $SERVER_CERT_DIR/server-certificate.pem -inkey $SERVER_CERT_DIR/server-private-key.key -passin pass:$PASSWORD -passout pass:$PASSWORD -name server -out $CERT_DIR/keystore.p12

# Creation of truststore with CA certificate
keytool -import -file $CA_CERT_DIR/ca-self-signed-certificate.pem -keystore $CERT_DIR/truststore.p12 -storetype PKCS12 -storepass $PASSWORD -noprompt -alias ca
