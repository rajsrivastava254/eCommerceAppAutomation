package utils;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonReaderUtil {

	public static  Object getAnyKeyValue(String filePath, String keyToFind) {
        try {
            byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
            String fileContent = new String(fileBytes, StandardCharsets.UTF_8);

            JSONObject jsonObject = new JSONObject(fileContent);
            return findValue(jsonObject, keyToFind);
        } catch (IOException e) {
            System.out.println("Error reading the jsonTestData file: " + e.getMessage());
        }
        return null;
    }
	


    public static  Object getValidKeyValue(String filePath, String keyToFind) {
        try {
            // Read JSON data from the file and parse it
            byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
            String fileContent = new String(fileBytes, StandardCharsets.UTF_8);

            JSONObject jsonObject = new JSONObject(fileContent);

            // Get the value associated with the specified key and return it using Optional
            return jsonObject.opt(keyToFind);
        } catch (IOException e) {
            System.out.println("Error reading the jsonTestData file: " + e.getMessage());
        }
        return null;
    }

	public static  List<String> getValidKeyValues(String filePath, String keyToFind) {
		try {
			JSONObject jsonObject = new JSONObject(new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8));
			return extractValues(jsonObject, keyToFind);
		} catch (IOException e) {
			System.out.println("Error reading the JSON file: " + e.getMessage());
		}
		return null;
	}

	public static  Object findValue(JSONObject jsonObject, String keyToFind) {
		Object value = jsonObject.opt(keyToFind);
		if (value != null) {
			return value;
		}

		for (String key : jsonObject.keySet()) {
			Object nestedValue = jsonObject.opt(key);
			if (nestedValue instanceof JSONObject) {
				Object result = findValue((JSONObject) nestedValue, keyToFind);
				if (result != null) {
					return result;
				}
			}
		}
		return null;
	}
	
	public static List<String> buildStringListFromObject(Object obj) {
	    List<String> stringList = new ArrayList<>();
	    
	    if (obj instanceof JSONArray) {
	        JSONArray jsonArray = (JSONArray) obj;
	        for (int i = 0; i < jsonArray.length(); i++) {
	            stringList.add(jsonArray.optString(i));
	        }
	    }
	    
	    return stringList;
	}

	public static  List<String> extractValues(JSONObject jsonObject, String keyToFind) {
		List<String> valuesList = new ArrayList<>();
		if (jsonObject.has(keyToFind)) {
			Object value = jsonObject.get(keyToFind);

			if (value instanceof JSONArray) {
				// If the value is an array, add its elements to the list
				JSONArray jsonArray = (JSONArray) value;
				for (int i = 0; i < jsonArray.length(); i++) {
					valuesList.add(jsonArray.getString(i));
				}
			} else {
				// If the value is a string, add it to the list
				valuesList.add(value.toString());
			}
		}
		return valuesList;
	}

	public static  Map<String, String> convertJsonObjectToHashMap(JSONObject jsonObject) {
		Map<String, String> resultMap = new HashMap<>();
		Iterator<String> keys = jsonObject.keys();

		while (keys.hasNext()) {
			String key = keys.next();
			String value = jsonObject.getString(key);
			resultMap.put(key, value);
		}

		return resultMap;
	}

	public static  JSONArray getJsonArray(String file, String keyofObject) {
		return (JSONArray) JsonReaderUtil.getValidKeyValue(file, keyofObject);
	}

	public static  Map<String, JSONArray> getMapOfJSONArrays(String fileName) {
		try (FileReader fileReader = new FileReader(fileName)) {
			JSONTokener tokener = new JSONTokener(fileReader);
			JSONObject jsonObject = new JSONObject(tokener);

			Map<String, JSONArray> resultMap = new HashMap<>();

			for (String key : jsonObject.keySet()) {
				JSONArray jsonArray = jsonObject.getJSONArray(key);
				resultMap.put(key, jsonArray);
			}

			return resultMap;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
