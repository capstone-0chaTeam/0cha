using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;

[System.Serializable]
public class CashInfo
{
    public long cash;
}
public class GetCash : MonoBehaviour
{
    private string url = "http://127.0.0.1:8000/getCashInfo"; // ���� ���� �ּҷ� ��ü
    private string account = "12345678"; // DB�� ������ ����

    void Start()
    {
        StartCoroutine(GetCashData(account));
    }

    IEnumerator GetCashData(string account)
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

                // �Ľ��� �����͸� ���
                CashInfo cashInfo = JsonUtility.FromJson<CashInfo>(jsonString);

                // ���� ������ Ȱ��
                Debug.Log("Cash: " + cashInfo.cash);
                
            }
        }
    }
}
