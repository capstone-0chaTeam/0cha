using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;

[System.Serializable]
public class StockInfo
{
    public string stockcode;
    public long quantityheld;
    public long purchaseprice;
}
public class GetStockList : MonoBehaviour
{
    private string url = "http://127.0.0.1:8000/getStockData"; // 실제 서버 주소로 대체
    private string account = "12345678"; // DB에 전달할 계좌

    void Start()
    {
        StartCoroutine(GetStockData(account));
    }

    IEnumerator GetStockData(string account)
    {
        // URL에 쿼리 문자열로 계정 정보를 추가
        string requestUrl = $"{url}?account={account}";

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

                // 배열로 직접 파싱
                StockInfo[] stockInfos = JsonHelper.FromJson<StockInfo>(jsonString);

                // 이후 데이터 활용
                foreach (StockInfo info in stockInfos)
                {
                    Debug.Log("Stock Code: " + info.stockcode);
                    Debug.Log("Quantity Held: " + info.quantityheld);
                    Debug.Log("Purchase Price: " + info.purchaseprice);
                }
            }
        }
    }
}

// JsonHelper 클래스
public static class JsonHelper
{
    public static T[] FromJson<T>(string json)
    {
        string newJson = "{ \"array\": " + json + "}";
        Wrapper<T> wrapper = JsonUtility.FromJson<Wrapper<T>>(newJson);
        return wrapper.array;
    }

    [System.Serializable]
    private class Wrapper<T>
    {
        public T[] array;
    }
}
