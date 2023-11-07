using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class overcoin : MonoBehaviour
{
    // Start is called before the first frame update
    public Text totalScoreText;

    void Start()
    {
        // PlayerPrefs를 사용하여 저장된 총 점수를 가져옴
        int totalScore = PlayerPrefs.GetInt("TotalScore", 0);

        // totalScoreText에 총 점수를 표시
        totalScoreText.text = "총 획득 금액 : " + totalScore + "원";
    }
}
