package dataProvider;

import com.google.common.reflect.TypeToken;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import constants.ApiPathConstants;
import dto.commonMongoDto.LatLongTestData;
import org.testng.annotations.DataProvider;
import utils.GenericHelper;
import utils.MongoHelper;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created By: Ankit Agarwal
 **/

public class DataProviderUtil {

    public Object[] testData(MongoCollection mongoCollection, Type listType) {
        return testData(mongoCollection, listType, new HashMap());
    }

    public Object[] testData(MongoCollection mongoCollection, Type listType, Map map) {
        map.put("executeTest", true);

        List<Object> testDataList = GenericHelper.convertFromJsonArray(
                GenericHelper.convertToJson(
                        MongoHelper.getResultFromMongo(mongoCollection, new BasicDBObject(map))),
                listType);
        Object[] testData = new Object[testDataList.size()];
        for (int i = 0; i < testDataList.size(); i++) {
            testData[i] = GenericHelper.convertToJson(testDataList.get(i));
        }
        return testData;
    }

    @DataProvider(name = "groferCityUsers")
    public Object[] gettingLatLngTestData(){
        Type listType = new TypeToken<List<LatLongTestData>>(){}.getType();
        Object[] objects = testData(MongoHelper.getMongoCollectionTND(ApiPathConstants.mongoDatabaseName,
                    ApiPathConstants.mongoCollectionName),listType);
        return objects;
    }
}
