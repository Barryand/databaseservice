import com.barry.dao.DbService;
import com.barry.po.ReferencePointData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Barry on 2018/1/29.
 */
public class DbServiceTest {
    @Test
    public void testImportReferencePoint() {
        DbService dbService = new DbService();
        String filename = "C:\\Users\\Barry\\Desktop\\本科毕业设计\\ReferencePointpoint_loc2.txt";
        String filename2 = "C:\\Users\\Barry\\Desktop\\本科毕业设计\\ReferencePointDataAdata1.txt";
        //dbService.importFileItself(filename, "233");
        //dbService.importFileContent(filename2);
        List<ReferencePointData> referencePointDataList = new ArrayList<ReferencePointData>();
        dbService.provideReferencePointData(referencePointDataList);
        for (ReferencePointData referencePointData : referencePointDataList) {
            System.out.print(referencePointData.getRpdId() + " ");
            System.out.print(referencePointData.getAP1() + " ");
            System.out.print(referencePointData.getRSSI1() + " ");
            System.out.print(referencePointData.getAP2() + " ");
            System.out.print(referencePointData.getRSSI2() + " ");
            System.out.print(referencePointData.getAP3() + " ");
            System.out.print(referencePointData.getRSSI3() + " ");
            System.out.print(referencePointData.getAP4() + " ");
            System.out.print(referencePointData.getRSSI4() + " ");
            System.out.print(referencePointData.getAP5() + " ");
            System.out.println(referencePointData.getRSSI5());
        }
    }
}
