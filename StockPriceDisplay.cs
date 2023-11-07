using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;
using TMPro; // TextMesh Pro를 사용하기 위한 네임스페이스
using System.Diagnostics;

public class StockPriceDisplay : MonoBehaviour
{
    public TextMeshProUGUI priceText; // UI에 주식 가격을 표시할 TextMeshProUGUI 요소
    public string apiUrl = "http://127.0.0.1:8000/stock-price/005930.KS"; // API 엔드포인트 주소로 바꿔주세요

    void Start()
    {
        StartCoroutine(FetchStockPriceRealtime());
    }

    IEnumerator FetchStockPriceRealtime()
    {
        while (true)
        {
            using (WWW www = new WWW(apiUrl))
            {
                yield return www;

                if (string.IsNullOrEmpty(www.error))
                {
                    StockPriceResponse response = JsonUtility.FromJson<StockPriceResponse>(www.text);

                    if (response != null)
                    {
                        priceText.text =  response.price.ToString("N0");
                    }
                    else
                    {
                        UnityEngine.Debug.LogError("JSON 파싱 오류");
                    }
                }
                else
                {
                    UnityEngine.Debug.LogError("API 요청 오류: " + www.error);
                }
            }

            yield return new WaitForSeconds(5.0f); // 예를 들어, 5초마다 업데이트
        }
    }
}

[System.Serializable]
public class StockPriceResponse
{
    public string symbol;
    public float price;
}