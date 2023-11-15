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
    private string account = "12345678"; // DB�� ������ ����
    private string stockcode = "�Ｚ����";    // DB�� ������ �����ڵ�

    void Start()
    {
        StartCoroutine(GetQuantityInfoFromServer(account, stockcode));
    }

    IEnumerator GetQuantityInfoFromServer(string account, string stockcode)
    {
        // URL�� ���� ���ڿ��� ���� ������ �ֽ� �ڵ带 �߰�
        string requestUrl = $"{url}?account={account}&stockcode={stockcode}";

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

                // JSON�� StockInfo ��ü�� �Ľ�
                StockInfo stockInfo = JsonUtility.FromJson<StockInfo>(jsonString);

                // ���� ������ Ȱ��
                Debug.Log("Quantity Held: " + stockInfo.quantityheld);
                Debug.Log("Stock Code: " + stockInfo.stockcode);
                
            }
        }
    }
}
