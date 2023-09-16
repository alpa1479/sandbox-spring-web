# Script should be executed from /bash_scripts folder
# -------------------------------------------------------------------------------------------------------------------------------------------------------------
# Utility commands:
# cd ./examples/https/bash_scripts
# ./generate-certificates.sh
# openssl verify -CAfile ../server/src/main/resources/certificates/ca/ca-self-signed-certificate.pem ../server/src/main/resources/certificates/server/server-certificate-chain.pem
# openssl x509 -noout -text -in ../server/src/main/resources/certificates/ca/ca-self-signed-certificate.pem
# openssl x509 -noout -text -in ../server/src/main/resources/certificates/intermediate/intermediate-certificate.pem
# openssl s_client -showcerts -connect google.com:443

CERT_DIR='../server/src/main/resources/certificates'
rm -rf $CERT_DIR
mkdir $CERT_DIR

CA_CERT_DIR=$CERT_DIR/ca
INTERMEDIATE_CERT_DIR=$CERT_DIR/intermediate
SERVER_CERT_DIR=$CERT_DIR/server
CLIENT_CERT_DIR=$CERT_DIR/client

mkdir $CA_CERT_DIR
mkdir $INTERMEDIATE_CERT_DIR
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

# Creation of intermediate private key
openssl genrsa -aes256 -passout pass:$PASSWORD -out $INTERMEDIATE_CERT_DIR/intermediate-private-key.key 4096
# Creation of intermediate CSR using intermediate private key
openssl req -new -key $INTERMEDIATE_CERT_DIR/intermediate-private-key.key -passin pass:$PASSWORD -subj "//CN=intermediate" -out $INTERMEDIATE_CERT_DIR/intermediate-certificate-signing-request.csr
# Creation of openssl.cnf config file to add constraint that this certificate may be used as CA certificate
echo "[ v3_intermediate_ca ]" >>$INTERMEDIATE_CERT_DIR/openssl.cnf
echo "basicConstraints = CA:TRUE" >>$INTERMEDIATE_CERT_DIR/openssl.cnf
# Creation of intermediate certificate using intermediate CSR and CA private key with CA certificate
openssl x509 -req -in $INTERMEDIATE_CERT_DIR/intermediate-certificate-signing-request.csr -CA $CA_CERT_DIR/ca-self-signed-certificate.pem -CAkey $CA_CERT_DIR/ca-private-key.key -passin pass:$PASSWORD -extfile $INTERMEDIATE_CERT_DIR/openssl.cnf -extensions v3_intermediate_ca -CAcreateserial -days $EXPIRATION_TIME -out $INTERMEDIATE_CERT_DIR/intermediate-certificate.pem

# Creation of server private key
openssl genrsa -aes256 -passout pass:$PASSWORD -out $SERVER_CERT_DIR/server-private-key.key 4096
# Creation of server CSR using server private key
openssl req -new -key $SERVER_CERT_DIR/server-private-key.key -passin pass:$PASSWORD -subj "//CN=localhost" -out $SERVER_CERT_DIR/server-certificate-signing-request.csr
# Creation of server certificate using server CSR and intermediate private key with intermediate certificate
openssl x509 -req -in $SERVER_CERT_DIR/server-certificate-signing-request.csr -CA $INTERMEDIATE_CERT_DIR/intermediate-certificate.pem -CAkey $INTERMEDIATE_CERT_DIR/intermediate-private-key.key -passin pass:$PASSWORD -CAcreateserial -days $EXPIRATION_TIME -out $SERVER_CERT_DIR/server-certificate.pem

# Creation of server certificate chain
cat $INTERMEDIATE_CERT_DIR/intermediate-certificate.pem >>$SERVER_CERT_DIR/server-certificate-chain.pem
cat $SERVER_CERT_DIR/server-certificate.pem >>$SERVER_CERT_DIR/server-certificate-chain.pem

# Creation of keystore with server certificate chain and private key
openssl pkcs12 -export -in $SERVER_CERT_DIR/server-certificate-chain.pem -inkey $SERVER_CERT_DIR/server-private-key.key -passin pass:$PASSWORD -passout pass:$PASSWORD -name server -out $CERT_DIR/keystore.p12

# Creation of truststore with CA certificate
keytool -import -file $CA_CERT_DIR/ca-self-signed-certificate.pem -keystore $CERT_DIR/truststore.p12 -storetype PKCS12 -storepass $PASSWORD -noprompt -alias ca

# -------------------------------------------------------------------------------------------------------------------------------------------------------------
# For https-client module:
HTTP_CLIENT_CERT_DIR='../client/src/main/resources/certificates'
rm -rf $HTTP_CLIENT_CERT_DIR
mkdir $HTTP_CLIENT_CERT_DIR

# Creation of client private key
openssl genrsa -aes256 -passout pass:$PASSWORD -out $CLIENT_CERT_DIR/client-private-key.key 4096
# Creation of client CSR using client private key
openssl req -new -key $CLIENT_CERT_DIR/client-private-key.key -passin pass:$PASSWORD -subj "//CN=client" -out $CLIENT_CERT_DIR/client-certificate-signing-request.csr
# Creation of client certificate using client CSR and client private key with CA certificate
openssl x509 -req -in $CLIENT_CERT_DIR/client-certificate-signing-request.csr -CA $CA_CERT_DIR/ca-self-signed-certificate.pem -CAkey $CA_CERT_DIR/ca-private-key.key -passin pass:$PASSWORD -days $EXPIRATION_TIME -out $CLIENT_CERT_DIR/client-certificate.pem

# Creation of keystore with client certificate
openssl pkcs12 -export -in $CLIENT_CERT_DIR/client-certificate.pem -inkey $CLIENT_CERT_DIR/client-private-key.key -passin pass:$PASSWORD -passout pass:$PASSWORD -name client -out $HTTP_CLIENT_CERT_DIR/keystore.p12
# Copying of truststore with CA certificate
cp $CERT_DIR/truststore.p12 $HTTP_CLIENT_CERT_DIR/truststore.p12

# -------------------------------------------------------------------------------------------------------------------------------------------------------------
# Manual certificate validation:

# Extraction of the public key from CA certificate
# 1. openssl x509 -in $INTERMEDIATE_CERT_DIR/intermediate-certificate.pem -noout -pubkey > $INTERMEDIATE_CERT_DIR/intermediate-certificate-public-key.pem

# 2. Extraction of the certificate signature
# openssl x509 -in $SERVER_CERT_DIR/server-certificate.pem -text -noout -certopt ca_default,no_validity,no_serial,no_subject,no_extensions,no_signame \
# | grep -v 'Signature Algorithm' \
# | tr -d '[:space:]:' \
# | xxd -r -p > $SERVER_CERT_DIR/server-signature.bin

# 3. Decryption of the signature using public key
# openssl rsautl -verify -inkey $INTERMEDIATE_CERT_DIR/intermediate-certificate-public-key.pem -in $SERVER_CERT_DIR/server-signature.bin -pubin > $SERVER_CERT_DIR/server-signature-decrypted.bin

# 4. Utility command to print the signature
# openssl asn1parse -inform der -in $SERVER_CERT_DIR/server-signature-decrypted.bin
# Example output:
#    0:d=0  hl=2 l=  49 cons: SEQUENCE
#    2:d=1  hl=2 l=  13 cons: SEQUENCE
#    4:d=2  hl=2 l=   9 prim: OBJECT            :sha256
#   15:d=2  hl=2 l=   0 prim: NULL
#   17:d=1  hl=2 l=  32 prim: OCTET STRING      [HEX DUMP]:932647B661D98F78742485B68287FDDF969FC0F28C272AFACB1DFEA4D9D7893F

# 5. Extraction of certificate body (all data except signature)
# openssl asn1parse -i -in $SERVER_CERT_DIR/server-certificate.pem -strparse 4 -out $SERVER_CERT_DIR/server-certificate-body.bin -noout

# 6. Recomputing hash of certificate body to verify that is has the same value as in signature. If hash is the same, it means that intermediate CA signed server-certificate
# openssl dgst -sha256 $SERVER_CERT_DIR/server-certificate-body.bin
# Example output:
# SHA256(./server-certificate-body.bin)= 932647b661d98f78742485b68287fddf969fc0f28c272afacb1dfea4d9d7893f
