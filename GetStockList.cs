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
    private string url = "http://127.0.0.1:8000/getStockData"; // ���� ���� �ּҷ� ��ü
    private string account = "12345678"; // DB�� ������ ����

    void Start()
    {
        StartCoroutine(GetStockData(account));
    }

    IEnumerator GetStockData(string account)
    {
        // URL�� ���� ���ڿ��� ���� ������ �߰�
        string requestUrl = $"{url}?account={account}";

        // UnityWebRequest�� ����Ͽ� ������ GET ��û ������
        using (UnityWebRequest www = UnityWebRequest.Get(requestUrl))
        {
            yield return www.SendWebRequest();

            if (www.result == UnityWebRequest.Result.ConnectionError || www.result == UnityWebRequest.Result.ProtocolError)
            {
                Debug.LogError("Error: " + www.error);
            }
            else
            {
                // �޾ƿ� JSON �����͸� �Ľ�
                string jsonString = www.downloadHandler.text;
                Debug.Log("Received JSON data: " + jsonString);

                // �迭�� ���� �Ľ�
                StockInfo[] stockInfos = JsonHelper.FromJson<StockInfo>(jsonString);

                // ���� ������ Ȱ��
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

// JsonHelper Ŭ����
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
