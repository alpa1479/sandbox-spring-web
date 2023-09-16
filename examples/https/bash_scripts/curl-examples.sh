# Script should be executed from /bash_scripts folder

# Case 1: TLS enabled, sending request without knowing CA of server certificate
curl -v https://localhost:8443/api/server-resource

# Example output:
# *   Trying 127.0.0.1:8443...
#   % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
#                                  Dload  Upload   Total   Spent    Left  Speed
#   0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0* Connected to localhost (127.0.0.1) port 8443 (#0)
# * ALPN, offering h2
# * ALPN, offering http/1.1
# * successfully set certificate verify locations:
# *  CAfile: C:/Program Files/Git/mingw64/ssl/certs/ca-bundle.crt
# *  CApath: none
# } [5 bytes data]
# * TLSv1.3 (OUT), TLS handshake, Client hello (1):
# } [512 bytes data]
# * TLSv1.3 (IN), TLS handshake, Server hello (2):
# { [122 bytes data]
#   0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0* TLSv1.3 (IN), TLS handshake, Encrypted Extensions (8):
# { [32 bytes data]
# * TLSv1.3 (IN), TLS handshake, Certificate (11):
# { [1209 bytes data]
# * TLSv1.3 (OUT), TLS alert, unknown CA (560):
# } [2 bytes data]
# * SSL certificate problem: unable to get local issuer certificate
#   0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0
# * Closing connection 0
# curl: (60) SSL certificate problem: unable to get local issuer certificate
# More details here: https://curl.se/docs/sslcerts.html
#
# curl failed to verify the legitimacy of the server and therefore could not
# establish a secure connection to it. To learn more about this situation and
# how to fix it, please visit the web page mentioned above.

# Case 2: TLS enabled, sending request specifying CA of server certificate
curl -v --cacert ../server/src/main/resources/certificates/ca/ca-self-signed-certificate.pem https://localhost:8443/api/server-resource

# Example output:
# *   Trying 127.0.0.1:8443...
#   % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
#                                  Dload  Upload   Total   Spent    Left  Speed
#   0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0* Connected to localhost (127.0.0.1) port 8443 (#0)
# * ALPN, offering h2
# * ALPN, offering http/1.1
# * successfully set certificate verify locations:
# *  CAfile: ../server/src/main/resources/certificates/ca/ca-self-signed-certificate.pem
# *  CApath: none
# } [5 bytes data]
# * TLSv1.3 (OUT), TLS handshake, Client hello (1):
# } [512 bytes data]
# * TLSv1.3 (IN), TLS handshake, Server hello (2):
# { [122 bytes data]
# * TLSv1.3 (IN), TLS handshake, Encrypted Extensions (8):
# { [32 bytes data]
# * TLSv1.3 (IN), TLS handshake, Certificate (11):
# { [1209 bytes data]
# * TLSv1.3 (IN), TLS handshake, CERT verify (15):
# { [520 bytes data]
# * TLSv1.3 (IN), TLS handshake, Finished (20):
# { [52 bytes data]
# * TLSv1.3 (OUT), TLS change cipher, Change cipher spec (1):
# } [1 bytes data]
# * TLSv1.3 (OUT), TLS handshake, Finished (20):
# } [52 bytes data]
# * SSL connection using TLSv1.3 / TLS_AES_256_GCM_SHA384
# * ALPN, server did not agree to a protocol
# * Server certificate:
# *  subject: CN=localhost
# *  start date: Aug 26 13:41:24 2023 GMT
# *  expire date: Aug 25 13:41:24 2024 GMT
# *  common name: localhost (matched)
# *  issuer: CN=CA
# *  SSL certificate verify ok.
# } [5 bytes data]
# > GET /api/server-resource HTTP/1.1
# > Host: localhost:8443
# > User-Agent: curl/7.79.1
# > Accept: */*
# >
# { [5 bytes data]
# * TLSv1.3 (IN), TLS handshake, Newsession Ticket (4):
# { [1468 bytes data]
# * Mark bundle as not supporting multiuse
# < HTTP/1.1 200
# < Content-Type: application/json
# < Transfer-Encoding: chunked
# < Date: Sat, 26 Aug 2023 13:53:43 GMT
# <
# { [53 bytes data]
# 100    47    0    47    0     0    542      0 --:--:-- --:--:-- --:--:--   546{"uuid":"0758ebc9-773d-49db-ac85-a34abffa5901"}
# * Connection #0 to host localhost left intact

# Case 3: TLS enabled, sending request specifying parameter to skip server certificate validation
curl -v --insecure https://localhost:8443/api/server-resource

# Case 4: mTLS enabled, sending request specifying CA of server certificate, but without client certificate
curl -v --cacert ../server/src/main/resources/certificates/ca/ca-self-signed-certificate.pem https://localhost:8443/api/server-resource

# Example output:
# *   Trying 127.0.0.1:8443...
#   % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
#                                  Dload  Upload   Total   Spent    Left  Speed
#   0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0* Connected to localhost (127.0.0.1) port 8443 (#0)
# * ALPN, offering h2
# * ALPN, offering http/1.1
# * successfully set certificate verify locations:
# *  CAfile: ../server/src/main/resources/certificates/ca/ca-self-signed-certificate.pem
# *  CApath: none
# } [5 bytes data]
# * TLSv1.3 (OUT), TLS handshake, Client hello (1):
# } [512 bytes data]
# * TLSv1.3 (IN), TLS handshake, Server hello (2):
# { [122 bytes data]
# * TLSv1.3 (IN), TLS handshake, Encrypted Extensions (8):
# { [32 bytes data]
# * TLSv1.3 (IN), TLS handshake, Request CERT (13):
# { [106 bytes data]
# * TLSv1.3 (IN), TLS handshake, Certificate (11):
# { [1209 bytes data]
# * TLSv1.3 (IN), TLS handshake, CERT verify (15):
# { [520 bytes data]
# * TLSv1.3 (IN), TLS handshake, Finished (20):
# { [52 bytes data]
# * TLSv1.3 (OUT), TLS change cipher, Change cipher spec (1):
# } [1 bytes data]
# * TLSv1.3 (OUT), TLS handshake, Certificate (11):
# } [8 bytes data]
# * TLSv1.3 (OUT), TLS handshake, Finished (20):
# } [52 bytes data]
# * SSL connection using TLSv1.3 / TLS_AES_256_GCM_SHA384
# * ALPN, server did not agree to a protocol
# * Server certificate:
# *  subject: CN=localhost
# *  start date: Aug 26 13:41:24 2023 GMT
# *  expire date: Aug 25 13:41:24 2024 GMT
# *  common name: localhost (matched)
# *  issuer: CN=CA
# *  SSL certificate verify ok.
# } [5 bytes data]
# > GET /api/server-resource HTTP/1.1
# > Host: localhost:8443
# > User-Agent: curl/7.79.1
# > Accept: */*
# >
# { [5 bytes data]
# * TLSv1.3 (IN), TLS alert, bad certificate (554):
# { [2 bytes data]
# * OpenSSL SSL_read: error:14094412:SSL routines:ssl3_read_bytes:sslv3 alert bad certificate, errno 0
#   0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0
# * Closing connection 0
# curl: (56) OpenSSL SSL_read: error:14094412:SSL routines:ssl3_read_bytes:sslv3 alert bad certificate, errno 0

# Case 5: mTLS enabled, sending request specifying CA of server certificate with client certificate and private key
curl -v --cacert ../server/src/main/resources/certificates/ca/ca-self-signed-certificate.pem --cert ../server/src/main/resources/certificates/client/client-certificate.pem:password --key ../server/src/main/resources/certificates/client/client-private-key.key https://localhost:8443/api/server-resource

# Example output:
# *   Trying 127.0.0.1:8443...
#   % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
#                                  Dload  Upload   Total   Spent    Left  Speed
#   0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0* Connected to localhost (127.0.0.1) port 8443 (#0)
# * ALPN, offering h2
# * ALPN, offering http/1.1
# * successfully set certificate verify locations:
# *  CAfile: ../server/src/main/resources/certificates/ca/ca-self-signed-certificate.pem
# *  CApath: none
# } [5 bytes data]
# * TLSv1.3 (OUT), TLS handshake, Client hello (1):
# } [512 bytes data]
# * TLSv1.3 (IN), TLS handshake, Server hello (2):
# { [122 bytes data]
# * TLSv1.3 (IN), TLS handshake, Encrypted Extensions (8):
# { [32 bytes data]
# * TLSv1.3 (IN), TLS handshake, Request CERT (13):
# { [106 bytes data]
# * TLSv1.3 (IN), TLS handshake, Certificate (11):
# { [1209 bytes data]
# * TLSv1.3 (IN), TLS handshake, CERT verify (15):
# { [520 bytes data]
# * TLSv1.3 (IN), TLS handshake, Finished (20):
# { [52 bytes data]
# * TLSv1.3 (OUT), TLS change cipher, Change cipher spec (1):
# } [1 bytes data]
# * TLSv1.3 (OUT), TLS handshake, Certificate (11):
# } [2400 bytes data]
# * TLSv1.3 (OUT), TLS handshake, CERT verify (15):
# } [520 bytes data]
# * TLSv1.3 (OUT), TLS handshake, Finished (20):
# } [52 bytes data]
# * SSL connection using TLSv1.3 / TLS_AES_256_GCM_SHA384
# * ALPN, server did not agree to a protocol
# * Server certificate:
# *  subject: CN=localhost
# *  start date: Aug 26 13:41:24 2023 GMT
# *  expire date: Aug 25 13:41:24 2024 GMT
# *  common name: localhost (matched)
# *  issuer: CN=CA
# *  SSL certificate verify ok.
# } [5 bytes data]
# > GET /api/server-resource HTTP/1.1
# > Host: localhost:8443
# > User-Agent: curl/7.79.1
# > Accept: */*
# >
# { [5 bytes data]
# * TLSv1.3 (IN), TLS handshake, Newsession Ticket (4):
# { [3858 bytes data]
# * Mark bundle as not supporting multiuse
# < HTTP/1.1 200
# < Content-Type: application/json
# < Transfer-Encoding: chunked
# < Date: Sat, 26 Aug 2023 14:13:43 GMT
# <
# { [53 bytes data]
# 100    47    0    47    0     0    284      0 --:--:-- --:--:-- --:--:--   286{"uuid":"d96534ca-9a59-4ff4-946f-e6313b933031"}
# * Connection #0 to host localhost left intact
