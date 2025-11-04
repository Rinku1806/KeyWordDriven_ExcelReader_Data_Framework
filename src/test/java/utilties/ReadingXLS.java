package utilties;


import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;

import util.Xls_Reader;

public class ReadingXLS {

	public static void main(String[] args) {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\data_Xls\\Data.xlsx";
		Xls_Reader xls = new Xls_Reader(filePath);
		String sheetName="Stock Suite";
		String flag="addfreshstock";
		int iterationNumber=1;
		JSONObject data =new ReadingXLS().getTestData(sheetName, flag, iterationNumber, filePath);
		System.out.println(data);
		

	}
	
	
	/*public static void copyFolder(Path source, Path target) throws IOException {
        // Use Files.walkFileTree to traverse source directory recursively
        Files.walkFileTree(source, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                // Create directories in target folder to match source
                Path targetDir = target.resolve(source.relativize(dir));
                if (Files.notExists(targetDir)) {
                    Files.createDirectories(targetDir);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                // Copy files to target folder, replacing if already exists
                Path targetFile = target.resolve(source.relativize(file));
                Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }
        });
    }*/
	
	public JSONObject getTestData(String sheetName,String flag,int iterationNumber,String xlsFilePath) {
		Xls_Reader xls = new Xls_Reader(xlsFilePath);
		int flagRowNumber=1;
		while(!xls.getCellData(sheetName, 0, flagRowNumber).equals(flag)) {
			flagRowNumber++;
		}
		System.out.println("Flag Row Number "+ flagRowNumber);
		int colNamesRowNumber = flagRowNumber-1;
		int dataStartRomNumber = flagRowNumber;
		int index=1;
		while(!xls.getCellData(sheetName, 0, dataStartRomNumber).equals("")) {
			int colIndex=0;
			if(index == iterationNumber) {
				JSONObject json = new JSONObject();
				while(!xls.getCellData(sheetName, colIndex , colNamesRowNumber).equals("")) {
					String data = xls.getCellData(sheetName, colIndex, dataStartRomNumber);
					String colName=xls.getCellData(sheetName, colIndex, colNamesRowNumber);
					System.out.println(colName+" -- "+data);
					json.put(colName, data);
					colIndex++;
				}
				return json;
			}else
				index++;
			
			System.out.println("-------");
			dataStartRomNumber++;
		}
		return new JSONObject();
	}
	
	
	
	public Map<String, String> getTestDataAsMap(String sheetName,String flag,int iterationNumber,String xlsFilePath) {
		Map<String, String> map = new HashMap<>(); 
		Xls_Reader xls = new Xls_Reader(xlsFilePath);
		int flagRowNumber=1;
		while(!xls.getCellData(sheetName, 0, flagRowNumber).equals(flag)) {
			flagRowNumber++;
		}
		System.out.println("Flag Row Number "+ flagRowNumber);
		int colNamesRowNumber = flagRowNumber-1;
		int dataStartRomNumber = flagRowNumber;
		int index=1;
		while(!xls.getCellData(sheetName, 0, dataStartRomNumber).equals("")) {
			int colIndex=0;
			if(index == iterationNumber) {
				JSONObject json = new JSONObject();
				while(!xls.getCellData(sheetName, colIndex , colNamesRowNumber).equals("")) {
					String data = xls.getCellData(sheetName, colIndex, dataStartRomNumber);
					String colName=xls.getCellData(sheetName, colIndex, colNamesRowNumber);
					System.out.println(colName+" -- "+data);
					map.put(colName, data);
					colIndex++;
				}
				return map;
			}else
				index++;
			
			System.out.println("-------");
			dataStartRomNumber++;
		}
		return new JSONObject();
	}
	
	
	
	
	
	
	

	public int getTestDataSets(String xlsFilePath, String dataflag, String sheetName) {
		Xls_Reader xls = new Xls_Reader(xlsFilePath);
		int flagRowNumber=1;
		while(!xls.getCellData(sheetName, 0, flagRowNumber).equals(dataflag)) {
			flagRowNumber++;
		}
		System.out.println("Flag Row Number "+ flagRowNumber);
		
		int dataStartRomNumber = flagRowNumber+2;
		int totalRows=0;
		while(!xls.getCellData(sheetName, 0, dataStartRomNumber).equals("")) {
			totalRows++;
			dataStartRomNumber++;
		}
		System.out.println("Total Rows " + totalRows);
		return totalRows;
	}

}
