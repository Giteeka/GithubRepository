package com.app.gitrepository

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

/**
 * Success Mock Response for api
 */
class SuccessDispatcher : Dispatcher(){

    var response = "[\n" +
            "  {\n" +
            "    \"author\": \"facebookresearch--------2\",\n" +
            "    \"name\": \"detectron2\",\n" +
            "    \"avatar\": \"https://github.com/facebookresearch.png\",\n" +
            "    \"url\": \"https://github.com/facebookresearch/detectron2\",\n" +
            "    \"description\": \"Detectron2 is FAIR's next-generation research platform for object detection and segmentation.\",\n" +
            "    \"language\": \"Python\",\n" +
            "    \"languageColor\": \"#3572A5\",\n" +
            "    \"stars\": 2903,\n" +
            "    \"forks\": 288,\n" +
            "    \"currentPeriodStars\": 651,\n" +
            "    \"builtBy\": [\n" +
            "      {\n" +
            "        \"username\": \"ppwwyyxx\",\n" +
            "        \"href\": \"https://github.com/ppwwyyxx\",\n" +
            "        \"avatar\": \"https://avatars1.githubusercontent.com/u/1381301\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"username\": \"brettkoonce\",\n" +
            "        \"href\": \"https://github.com/brettkoonce\",\n" +
            "        \"avatar\": \"https://avatars1.githubusercontent.com/u/11281814\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"username\": \"Jakobu5\",\n" +
            "        \"href\": \"https://github.com/Jakobu5\",\n" +
            "        \"avatar\": \"https://avatars3.githubusercontent.com/u/32122722\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"username\": \"jsoref\",\n" +
            "        \"href\": \"https://github.com/jsoref\",\n" +
            "        \"avatar\": \"https://avatars3.githubusercontent.com/u/2119212\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"username\": \"rbgirshick\",\n" +
            "        \"href\": \"https://github.com/rbgirshick\",\n" +
            "        \"avatar\": \"https://avatars2.githubusercontent.com/u/1190634\"\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"author\": \"uber-go\",\n" +
            "    \"name\": \"guide\",\n" +
            "    \"avatar\": \"https://github.com/uber-go.png\",\n" +
            "    \"url\": \"https://github.com/uber-go/guide\",\n" +
            "    \"description\": \"The Uber Go Style Guide.\",\n" +
            "    \"stars\": 3908,\n" +
            "    \"forks\": 320,\n" +
            "    \"currentPeriodStars\": 972,\n" +
            "    \"builtBy\": [\n" +
            "      {\n" +
            "        \"username\": \"abhinav\",\n" +
            "        \"href\": \"https://github.com/abhinav\",\n" +
            "        \"avatar\": \"https://avatars1.githubusercontent.com/u/41730\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"username\": \"prashantv\",\n" +
            "        \"href\": \"https://github.com/prashantv\",\n" +
            "        \"avatar\": \"https://avatars3.githubusercontent.com/u/140159\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"username\": \"codygibb\",\n" +
            "        \"href\": \"https://github.com/codygibb\",\n" +
            "        \"avatar\": \"https://avatars2.githubusercontent.com/u/6457106\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"username\": \"ribice\",\n" +
            "        \"href\": \"https://github.com/ribice\",\n" +
            "        \"avatar\": \"https://avatars0.githubusercontent.com/u/5403700\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"username\": \"jpreese\",\n" +
            "        \"href\": \"https://github.com/jpreese\",\n" +
            "        \"avatar\": \"https://avatars2.githubusercontent.com/u/6980197\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "]"


    override fun dispatch(request: RecordedRequest): MockResponse {
        val errorResponse = MockResponse().setResponseCode(404)
//        val pathWithoutQueryParams = Uri.parse(request.path).path ?: return errorResponse
        return MockResponse().setResponseCode(200).setBody(response)
    }

}