import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApiTest {

    List<Boolean> installmentList=new ArrayList<>();

    @Before
    public void before(){
        RestAssured.baseURI="https://www.postman.com/collections/5d9f72679607a60f23af";
        System.out.println("Tests Started.");
    }

    @Test
    public void CheckInstallmentIs1(){
        RequestSpecification request=RestAssured
                .given();

        Response response=request
                .when()
                .get(RestAssured.baseURI)
                .then()
                .extract().response();

        String installmentValue=response.jsonPath().getString("item[0].name").split("=")[1];
        if(installmentValue.equals("1")){
            String body =response.jsonPath().getString("item[0].response.body[0]");
            JSONObject jsonObj = new JSONObject(body);
            JSONArray products = jsonObj.getJSONObject("result").getJSONObject("data").getJSONArray("products");

            for(int i=0;i<products.length();i++){

                Boolean installment=  products.getJSONObject(i).getBoolean("installment");
                String installmentText= products.getJSONObject(i).getString("installmentText");
                Integer productGroupId=products.getJSONObject(i).getInt("productGroupId");
                installmentList=Helpers.convertParams(installment,installmentText,productGroupId);
                System.out.println(installmentList);
                Assert.assertEquals("Installment's field must be true but there is installment's field is false.",Constants.installmentIs1,installmentList);
            }
        }

        else {
            System.out.println("Installment's field isn't 1");
        }


    }

    @Test
    public void CheckInstallmentIs0(){
        RequestSpecification request=RestAssured
                .given();

        Response response=request
                .when()
                .get(RestAssured.baseURI)
                .then()
                .extract().response();
        String installmentValue=response.jsonPath().getString("item[1].name").split("=")[1];
        if(installmentValue.equals("0"))
        {
            String body =response.jsonPath().getString("item[1].response.body[0]");
            JSONObject jsonObj = new JSONObject(body);
            JSONArray products = jsonObj.getJSONObject("result").getJSONObject("data").getJSONArray("products");

            for(int i=0;i<products.length();i++){

                Boolean installment=  products.getJSONObject(i).getBoolean("installment");
                String installmentText= products.getJSONObject(i).getString("installmentText");
                Integer productGroupId=products.getJSONObject(i).getInt("productGroupId");
                installmentList=Helpers.convertParams(installment,installmentText,productGroupId);
                System.out.println(installmentList);
                Assert.assertEquals("Installment's field must be false but there is installment's field is true.",Constants.installmentIs2,installmentList);
            }

        }
        else {
            System.out.println("Installment's field isn't 0.");
        }

    }

    @Test
    public void CheckInstallmentIsNull(){
        RequestSpecification request=RestAssured
                .given();

        Response response=request
                .when()
                .get(RestAssured.baseURI)
                .then()
                .extract().response();

        String body =response.jsonPath().getString("item[3].response.body[0]");
        Assert.assertEquals("Installment's field not null.",null,body);

    }

    @After
    public void after(){
        System.out.println("Tests Completed.");
    }


}
