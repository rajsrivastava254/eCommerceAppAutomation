package utils;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DataProviders {

	@DataProvider(name = "userData")
	public Object[][] getData() throws IOException{
		List<HashMap<String, String>>	data =getJsonData(System.getProperty("user.dir")+"//src//test//java//utils//eCommerce.json");
		return new Object[][] {{data.get(0)}};
}

    public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
		// convert json file content to json string(using common io)
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath),StandardCharsets.UTF_8);

		// convert json string into hashmap(using jackson dependency)
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;

	}
}
