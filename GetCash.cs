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
    private string url = "http://127.0.0.1:8000/getCashInfo"; // 실제 서버 주소로 대체
    private string account = "12345678"; // DB에 전달할 계좌

    void Start()
    {
        StartCoroutine(GetCashData(account));
    }

    IEnumerator GetCashData(string account)
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

                // 파싱한 데이터를 사용
                CashInfo cashInfo = JsonUtility.FromJson<CashInfo>(jsonString);

                // 이후 데이터 활용
                Debug.Log("Cash: " + cashInfo.cash);
                
            }
        }
    }
}
