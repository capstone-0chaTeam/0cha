using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;

[System.Serializable]
public class QuantityInfo
{
    public long quantityheld;
    public string stockcode;
}
public class GetStockQuantity : MonoBehaviour
{
    private string url = "http://127.0.0.1:8000/getQuantityInfo"; 
    private string account = "12345678"; // DB에 전달할 계좌
    private string stockcode = "삼성전자";    // DB에 전달할 종목코드

    void Start()
    {
        StartCoroutine(GetQuantityInfoFromServer(account, stockcode));
    }

    IEnumerator GetQuantityInfoFromServer(string account, string stockcode)
    {
        // URL에 쿼리 문자열로 계정 정보와 주식 코드를 추가
        string requestUrl = $"{url}?account={account}&stockcode={stockcode}";

        // UnityWebRequest를 사용하여 서버에 GET 요청 보내기
        using (UnityWebRequest www = UnityWebRequest.Get(requestUrl))
        {
            yield return www.SendWebRequest();

            if (www.result == UnityWebRequest.Result.ConnectionError || www.result == UnityWebRequest.Result.ProtocolError)
            {
                Debug.LogError("Error: " + www.error);
            }
            else
            {
                // 받아온 JSON 데이터를 파싱
                string jsonString = www.downloadHandler.text;
                Debug.Log("Received JSON data: " + jsonString);

                // JSON을 StockInfo 객체로 파싱
                StockInfo stockInfo = JsonUtility.FromJson<StockInfo>(jsonString);

                // 이후 데이터 활용
                Debug.Log("Quantity Held: " + stockInfo.quantityheld);
                Debug.Log("Stock Code: " + stockInfo.stockcode);
                
            }
        }
    }
}
