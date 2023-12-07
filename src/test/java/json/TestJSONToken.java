package json;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

import org.junit.Test;

import com.google.gson.Gson;

import auth.login.LoginReply;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TestJSONToken {

    @Test
    public void testMalformedURL() throws MalformedURLException {
        // URL url = new URL("https://graph.facebook.com/search?q=java&type=post");
        // URL url = new
        // URL("https://ctpoixww04.execute-api.us-east-1.amazonaws.com/dev/login");

        // try (InputStream is = url.openStream(); JsonParser parser =
        // Json.createParser(is)) {
        try {
            URL url = new URL("https://ctpoixww04.execute-api.us-east-1.amazonaws.com/dev/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = "{\"id\":\"10295765\",\"password\":\"Value!12\"}";

            OutputStream os = conn.getOutputStream();
            // 1.The client sends their credentials (username and password) to the server.
            os.write(input.getBytes());
            os.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            JsonParser parser = (JsonParser) Json.createReader(br);

            while (parser.hasNext()) {
                Event e = parser.next();
                if (e == Event.KEY_NAME) {
                    switch (parser.getString()) {
                        case "RefreshToken":
                            parser.next();
                            System.out.print(parser.getString());
                            System.out.print(": ");
                            break;
                        case "IdToken":
                            parser.next();
                            System.out.println(parser.getString());
                            System.out.println("---------");
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void formatTokenJSONResponse() {
        String replyJson = "{\"ChallengeParameters\":{},\"AuthenticationResult\":{\"AccessToken\":\"eyJraWQiOiJ6M2pWR1lscDVsS2VHVzRXMkwrU3BtdjN4UzIwWGFCU1FvZFlvdWg1WkU4PSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiI2MWI5ZTI4NC1kMzVhLTQ0M2YtOTlhNy04MGY0OWE4YTI4NmQiLCJ0b2tlbl91c2UiOiJhY2Nlc3MiLCJzY29wZSI6ImF3cy5jb2duaXRvLnNpZ25pbi51c2VyLmFkbWluIiwiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLnVzLWVhc3QtMS5hbWF6b25hd3MuY29tXC91cy1lYXN0LTFfMEhkMUtvTTVhIiwiZXhwIjoxNTA1MTIwNzM1LCJpYXQiOjE1MDUxMTcxMzUsImp0aSI6ImNiMjdkZjJiLTIwOWQtNGU1MC05ZTI4LThjYTAyOGY1MTc5MyIsImNsaWVudF9pZCI6IjFlOHRlZm1sY2wyaDI5MHZuaDlyY2VmbnZyIiwidXNlcm5hbWUiOiIxMDI5NTc2NSJ9.ZGB_kCYoU4vDREr6teZQyIyRT2MZkpmGyleAgsgzMRIauq7RpqgL_HUZukqSK6E5mtNxux6hihzl5CaP9jcY26u4zJTnYASBJwE4iqqggNqhp82PtFdxipf5ZvYAyF3uVTPCeiRGrxJSyoQpEWVUPDYaqj1ak1b2oTExH8sG1oOmYcaACAWY4mkjueFJCrgcWov3SyWDyMKCE7ZhaloJkcJ2uZypztB42XQ67z96bxTvRozzEVEVT4_4szpMjEj3Nmxy9kIv8H7kNMUrb5tE4vTfG-kzctCq7YxU2FfJhDTR_feoRohcjWZp-Zyl2BYFjNXl42ZZEk-TlLHov73izA\",\"ExpiresIn\":3600,\"TokenType\":\"Bearer\",\"RefreshToken\":\"eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2R0NNIiwiYWxnIjoiUlNBLU9BRVAifQ.Wa12tUlCQHIjWvDx8BdhyQdftCwlFXb5W99N8njK3vBTNiP2vUfCGgdbWTWCCg4Z9_4RkHRy9e9m2oQnLqWRju4pLDBQvhsd0XhGg43T6yalnH7vXpqmzDnsBS270CsN0briBlBiCTvmU2eVeauPxCKlW_-fFZFXJRmXdFCit-dhufODi08-SpZOnHKFSq9W4kjk3OjUxUONKCMAZh0oWgMKC-46Kh1j02NGej9vW-Dc7yaqAzMn3JcdQtjC8w9syqD3qJOo0eMbXoJz3cje7b-ujIlknsfmmy6rewoYIusxV_ZVP6QJH09k0QbVqbzZguE38hnbSMtrCnMAMr2Pzw.2T_M51x_qvo2s2Kg.gIpF-UmqE2-g9TuyypFAQwp7DsUNRBdqzwaaVlkUTpcX6Ni2YH-7fd9GOlzna_ET7pDbGCT4NL8xomo6hq_nOEQJvRdexvBc9Hpy_l_mIEG91W7wKzvDzjEben6ZnaxFg3XkRVL1o8nnjVswmrImuUqSJlisa2eSrf1wM7pK7l8l-CwbEZ-kutdCZGJU0EHk4_12D_0D9tJYppfM63SmNsTyTZkpHO4z1nmhxNuEPL0Jo2ClbxDDWALzvdee193x-d7KeCJw75gVLd64rhaUPATnW3JznhHlg2ZjTtwK1PBDRRMjd7gblgtOif6QXCAeUPGqmd7V2MtRw5pmcEgSF9kqRByvt9p1RPyBDkZw9K84LG0U1kuyA4_TIxvCX35wwUlBmF6UPO7AcM__zCxi7pKuAcOgfCsongWnjX97DZuT5INT3gnDz48GG3qguiEFuq1WSzJOQrkFFrVCJcjwfIbuxPJ6OTt6f6NKhwZqTMHgRpvtvNgSh7qARszqj2-HqkmrEgKdjUdLGEc-toS0mj9aTH8GOG6Xu4poEoIElDx8BjZGz4cOyT2VWL4BDFg3OzXfafEIu1F1SnhI9K4A59G8JR1H2UKZV7iGS2zMeMROWvtIzWrZ3M2BwWLlkM80BzpK1Xb2ZnQXfmY_-tVOojimji4z-hrLAKwvfbgEpGd9vLfItRZn2ivmsBSlc2da7zCqP7bHbtCHXpR5F-ssxP2G_UbIYraBr9m6GQPo1BXvveJFTFxd976hjbY5VyNwHFa0aR2a-sVDUDdPzoaBJQqA-xRQllwQYCGIcb-daBg4IDah_qthMPvDN9bblUpO4AJs8951wSCmaz3VqFnBAk9IHFSO1MRY5eMgH7cHn6vbzsScyb8S8C4hhdgx9uu9Kr30DaHrSZF8dYK0Y9n8K8sD-ZBXtKwhJL7F3tVdcnmFcGKLToV6xJnJnZ5irnOODMJMBPmOY5G3RYPd8lGvzlOfPDe0_lwUX8O1oCVOtiA2C9rOmrRTcaNKlP8R7uIVBOlFOypPMnwyqV233kGVE1Rp6RgCsw7ZIwOUNJmgLGZDLePocCBgZVrGE2GLVG78q6Z_agKjxjAOXEbtVOrk3QIycdyTVN2DEjzt88CW-fHeSqc6K4H5BiBf_zmlVaiSciPXsqg.zouwNDBSKRVFp88zPU2pUA\",\"IdToken\":\"eyJraWQiOiJKQWJRaHNlTTVldFRBMk1vMlpoNGs5Mlp2ZmFHaU5zWWtVQlFNbG1cL1Mybz0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI2MWI5ZTI4NC1kMzVhLTQ0M2YtOTlhNy04MGY0OWE4YTI4NmQiLCJhdWQiOiIxZTh0ZWZtbGNsMmgyOTB2bmg5cmNlZm52ciIsImNvZ25pdG86Z3JvdXBzIjpbImFkbWluaXN0cmF0b3IiXSwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJ0b2tlbl91c2UiOiJpZCIsImF1dGhfdGltZSI6MTUwNTExNzEzNSwiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLnVzLWVhc3QtMS5hbWF6b25hd3MuY29tXC91cy1lYXN0LTFfMEhkMUtvTTVhIiwiY29nbml0bzp1c2VybmFtZSI6IjEwMjk1NzY1IiwiZXhwIjoxNTA1MTIwNzM1LCJpYXQiOjE1MDUxMTcxMzUsImVtYWlsIjoiam9uYXRoYW4udEB4dHJhLmNvLm56In0.gGcx2YYcjWzNvzlpPklry4Jt59GwDQuDRRIFfJGaf06hMKi0Cin31Ox7AD4CaRUmbUXzxh395Bb6bPS9cGdh5oa2hIveC27bwHpO7Eij6voEXdifS98_CTk_zKrAfH1U4qAHapemHA9eQqNnrGm0PtNHTo9Nh7ptrU_04Fc9askTjUnj3fOuDQTp1bLEJwwHbV7oBeQQlA6pYXvWvRwpj0col8N904w4QxoNNNEtYxIYbLtJkj3w-ZogJ9VPkKL_B3ayKTfle3nQ-PHrXyzYpBK89h39Hp2IiOddY-Ihl8iVuj8RziTUiMZ-0jTOToCAa8gQ813DGAtca0i4ffw3hg\"}}";
        Gson gson = new Gson();

        // Restaurant restaurantObject = gson.fromJson(restaurantJson,
        // Restaurant.class);
        LoginReply loginReplyObject = gson.fromJson(replyJson, LoginReply.class);

        // Object loginReplyObject = gson.fromJson(replyJson, Object.class);
        System.out.println("value " + loginReplyObject.getAuthenticationResult().getIdToken());
        System.out.println("obtain token "+ loginReplyObject.getAuthenticationResult().getAccessToken());
    }    
}
