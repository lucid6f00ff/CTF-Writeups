import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.encoders.Base64;

public class pgp_valid{
    public void main (String[] args){
        try {
            PGPPublicKey pkey = PGPEncryptionUtils.readPublicKey(new FileInputStream(new File(HOME_DIR + "to_verify")));
            Iterator it = pkey.getSignatures();

            PGPPublicKey signing_key = PGPEncryptionUtils.readPublicKey(
                    new FileInputStream(new File(HOME_DIR + "my_public_key")));

            while (it.hasNext()) {
                PGPSignature sig = (PGPSignature) it.next();
                sig.init(new JcaPGPContentVerifierBuilderProvider().setProvider("BC"), signing_key);
                // Here I'd expect to see at least a "true".
                println(sig.verify());
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PGPException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}