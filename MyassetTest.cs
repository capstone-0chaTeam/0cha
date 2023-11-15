using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;

public class MyassetData
{
    public string account;
    public long cash;
    public long balance;
    public long purchaseamount_all;
}
public class MyassetTest : MonoBehaviour
{
    private string apiUrl = "http://127.0.0.1:8000/save-asset";

    //������ ���� ������ �� ����
    private string account = "01778561";
    private long cash = 250000;
    private long balance = 200000;
    private long purchaseamount_all = 1300;

    void Start()
    {
        StartCoroutine(SendPostRequest());
    }

    IEnumerator SendPostRequest()
    {
        // �����͸� ��ü�� ����ϴ�.
        MyassetData assetData = new MyassetData
        {
            account = account,
            cash = cash,
            balance = balance,
            purchaseamount_all = purchaseamount_all
        };

        // ��ü�� JSON ���ڿ��� ��ȯ�մϴ�.
        string jsonData = JsonUtility.ToJson(assetData);

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
