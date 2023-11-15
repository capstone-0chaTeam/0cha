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

    //������ ���� ������ �� ����
    private string account = "01778561";
    private string stockcode = "�Ｚ����";
    private long purchaseprice = 68000; 
    private long quantityheld = 1300;

    void Start()
    {
        StartCoroutine(SendPostRequest());
    }

    IEnumerator SendPostRequest()
    {
        // �����͸� ��ü�� ����ϴ�.
        StockListData stockData = new StockListData
        {
            account = account,
            stockcode = stockcode,
            purchaseprice = purchaseprice,
            quantityheld = quantityheld
        };

        // ��ü�� JSON ���ڿ��� ��ȯ�մϴ�.
        string jsonData = JsonUtility.ToJson(stockData);

        // UnityWebRequest�� �����ϰ� �����մϴ�.
        UnityWebRequest request = new UnityWebRequest(apiUrl, "POST");
        byte[] bodyRaw = System.Text.Encoding.UTF8.GetBytes(jsonData);
        request.uploadHandler = (UploadHandler)new UploadHandlerRaw(bodyRaw);
        request.downloadHandler = (DownloadHandler)new DownloadHandlerBuffer();
        request.SetRequestHeader("Content-Type", "application/json");

        // ��û�� �����ϴ�.
        yield return request.SendWebRequest();

        // ������ ó���մϴ�.
        if (request.result == UnityWebRequest.Result.Success)
        {
            Debug.Log("POST ����: " + request.downloadHandler.text);
        }
        else
        {
            Debug.LogError("POST ����: " + request.error);
        }
    }
}
