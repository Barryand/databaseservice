import com.barry.dao.DbService;
import org.junit.Test;

/**
 * Created by Barry on 2018/1/29.
 */
public class DbServiceTest {
    @Test
    public void testImportReferencePoint() {
        DbService dbService = new DbService();
        String filename1 = "src\\main\\resources\\ReferencePointpoint_loc2.txt";
        String filename2 = "src\\main\\resources\\ReferencePointDataAdata1.txt";
        //dbService.importFileItself("233", filename1);
        //dbService.importFileItself("233", filename2);
        //dbService.importFileContent(filename1);
        //dbService.importFileContent(filename2);
        //dbService.deleteUserFile("233", filename2);
        /* dbService.importFileContent(filename2);
        List<ReferencePoint> referencePointList = new ArrayList<ReferencePoint>();
        dbService.provideReferencePoint(referencePointList);
        for (ReferencePoint referencePoint : referencePointList) {
            System.out.print(referencePoint.getRp_id() + " ");
            System.out.print(referencePoint.getLongitude() + " ");
            System.out.println(referencePoint.getLatitude());
        }

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
        }*/
    }
}
