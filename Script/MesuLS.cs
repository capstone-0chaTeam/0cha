using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class MesuLS : MonoBehaviour
{
    private Button yourButton;
    private GameObject gameManager;

    void Start()
    {
        // 버튼을 코드로 찾습니다. 버튼의 이름이 "YourButton"인 경우
        yourButton = GameObject.Find("ButtonMesu").GetComponent<Button>();

        // 버튼에 클릭 이벤트 리스너를 추가합니다.
        yourButton.onClick.AddListener(TaskOnClick);

        gameManager = GameObject.Find("GameManager");
        if (gameManager != null)
        {
            // GameManager를 찾았을 때 실행할 코드를 작성합니다.
            Debug.Log("GameManager를 찾음.");
        }
        else
        {
            // GameManager를 찾지 못했을 때 처리
            Debug.LogError("GameManager를 찾을 수 없습니다.");
        }
    }

    // Update is called once per frame
    void Update()
    {

    }
    void TaskOnClick()
    {
        Debug.Log("버튼이 클릭되었습니다.");

        // GameManager 오브젝트가 있는 경우 GameManager 스크립트의 메서드를 호출할 수도 있습니다.
        if (gameManager != null)
        {
            GameManager gameManagerScript = gameManager.GetComponent<GameManager>();
            if (gameManagerScript != null)
            {
                // "Samsung" 변수를 생성하고 DoSomething 메서드로 전달합니다.
                string ecoproVariable = "LS";
                gameManagerScript.DoSomething(ecoproVariable);
            }
            else
            {
                Debug.LogError("GameManager 스크립트를 찾을 수 없습니다.");
            }
        }
    }
}
