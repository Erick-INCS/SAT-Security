import io.Source
import java.security.Signature
import java.security.KeyFactory
import java.security.spec.PKCS8EncodedKeySpec
import java.util.Base64
import java.nio.charset.Charset


val key = Source.fromFile("key.pem").
  mkString.
  replaceAll("\n", "").
  replace("-----BEGIN PRIVATE KEY-----", "").
  replace("-----END PRIVATE KEY-----", "")

val kf = KeyFactory.getInstance("RSA");
val keySpecPKCS8 = new PKCS8EncodedKeySpec(
  Base64.getDecoder().decode(key))

val privKey = kf.generatePrivate(keySpecPKCS8);

var sign = Signature.getInstance("SHA256withRSA")
var cadena = Source.fromFile("cadena_original.txt").mkString.trim

sign.initSign(privKey)
sign.update(cadena.getBytes(Charset.forName("ISO-8859-1")))

val result = sign.sign()
print("\n\nBASE64:\n")
print(Base64.getEncoder().encodeToString(result))
