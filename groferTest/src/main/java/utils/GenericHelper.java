package utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.gson.*;
import dto.TaskPerUserDto;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created By: Ankit Agarwal
 **/

public class GenericHelper {

    public static String convertToJson(Object obj) {
        Gson gsonConverter = (new GsonBuilder()).disableHtmlEscaping().setPrettyPrinting().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").registerTypeAdapter(ObjectId.class, new JsonSerializer<ObjectId>() {
            @Override
            public JsonElement serialize(ObjectId src, Type typeOfSrc, JsonSerializationContext context) {
                return new JsonPrimitive(src.toHexString());
            }
            public void serialize(ObjectId arg0, JsonGenerator arg1, SerializerProvider arg2)
                    throws IOException, JsonProcessingException {
            }
        }).create();
        return gsonConverter.toJson(obj);
    }

    public static <T> T convertFromJson(String jsonData, Type type) {
        Gson gsonConverter = (new GsonBuilder()).disableHtmlEscaping().setPrettyPrinting().create();
        return gsonConverter.fromJson(jsonData, type);
    }

    public static  <T> List<T> convertFromJsonArray(String jsonArray, Type listType) {
        Gson gsonConverter = (new GsonBuilder()).disableHtmlEscaping().setPrettyPrinting().create();
        return (List)gsonConverter.fromJson(jsonArray, listType);
    }

    public static void getUserPercentage(List<TaskPerUserDto> todos, List<Integer> user, Integer percentage){
        Integer failure = 0;
        Integer completed = 0;
        Integer calculatedPercentage = 0;
        for (Integer i: user){
            for (TaskPerUserDto taskPerUserDto : todos){
                if (taskPerUserDto.getUserId().equals(i)){
                    if (taskPerUserDto.getCompleted() == false){
                        failure = failure+1;
                    }else {
                        completed = completed+1;
                    }
                }
            }
            calculatedPercentage = completed*100/(completed+failure);
            if (calculatedPercentage > percentage){
                System.out.println(i);
            }
            failure = 0;
            completed = 0;
        }
    }

}
