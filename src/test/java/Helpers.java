import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Helpers {

    //Burada tanımladığım sabitlere uygun olması için parametrelerimi listeye çevirdim.
    public static List<Boolean>  installmentList=new ArrayList<>();

    public static List<Boolean> convertParams(Boolean installment, String installmentText, Integer productGroupId){
        if(installment)
        {
            Boolean installmentTextStatus=true;
            Boolean  productGroupIdStatus=true;
            installmentList= Arrays.asList(installment,installmentTextStatus,productGroupIdStatus);
        }
        else
        {
            Boolean installmentTextStatus=false;
            Boolean  productGroupIdStatus=false;
            installmentList= Arrays.asList(installment,installmentTextStatus,productGroupIdStatus);
        }
        return installmentList;
    }
}
