using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;

[System.Serializable]
public class StockListData
{
    public string account;
    public string stockcode;
    public long purchaseprice;
    public long quantityheld;
}
public class StockListTest : MonoBehaviour
{
    private string apiUrl = "http://127.0.0.1:8000/process_data";

    //보내는 실제 값으로 값 변경
    private string account = "01778561";
    private string stockcode = "삼성전자";
    private long purchaseprice = 68000; 
    private long quantityheld = 1300;

    void Start()
    {
        StartCoroutine(SendPostRequest());
    }

    IEnumerator SendPostRequest()
    {
        // 데이터를 객체로 만듭니다.
        StockListData stockData = new StockListData
        {
            account = account,
            stockcode = stockcode,
            purchaseprice = purchaseprice,
            quantityheld = quantityheld
        };

        // 객체를 JSON 문자열로 변환합니다.
        string jsonData = JsonUtility.ToJson(stockData);

        // UnityWebRequest를 생성하고 설정합니다.
        UnityWebRequest request = new UnityWebRequest(apiUrl, "POST");
        byte[] bodyRaw = System.Text.Encoding.UTF8.GetBytes(jsonData);
        request.uploadHandler = (UploadHandler)new UploadHandlerRaw(bodyRaw);
        request.downloadHandler = (DownloadHandler)new DownloadHandlerBuffer();
        request.SetRequestHeader("Content-Type", "application/json");

        // 요청을 보냅니다.
        yield return request.SendWebRequest();

        // 응답을 처리합니다.
        if (request.result == UnityWebRequest.Result.Success)
        {
            Debug.Log("POST 성공: " + request.downloadHandler.text);
        }
        else
        {
            Debug.LogError("POST 실패: " + request.error);
        }
    }
}
