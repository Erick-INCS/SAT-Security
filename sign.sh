openssl pkcs8 -inform DER -in AAL0409235E6_1012231310.key -out key.pem -passin pass:delfin40
openssl dgst -sha256 -out signature -sign key.pem cadena_original.txt
openssl enc -in signature -a -A -out signB64.txt
cat signB64.txt
