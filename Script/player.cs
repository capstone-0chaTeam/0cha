using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class player : MonoBehaviour
{
    // Start is called before the first frame update

    bool isJump = false;
    bool isTop = false;
    public float jumpH = 0;
    public float jumpSpeed = 0;
    public Text scoreText;
    public int score = 0;
    public Text timeText;

    public AudioClip coinsound;
    private AudioSource backsound;
    public AudioClip oversound;
    

    Vector2 startPosition;
    Animator animator;
    void Start()
    {
        startPosition = transform.position;
        animator = GetComponent<Animator>();
        timeText.text = "남은 시간 : " + 0 + "초";
        scoreText.text = "획득 금액 : " + 0 + "원";

        backsound = GetComponent<AudioSource>();

    }

    // Update is called once per frame
    void Update()
    {
        if (Time.timeScale > 0) // Only update time if the game is not paused
        {
            float remainingTime = Mathf.Round(60 - Time.time);
            timeText.text = "남은 시간 : " + remainingTime + "초"; // 60 - Time.time를 사용하여 60초부터 감소시킴

            // 시간이 0이 되면 Game Over 씬으로 전환
            if (remainingTime <= 0)
            {
                GameOver();
            }
        }

        animator.SetBool("run", true);

        if (Input.GetMouseButtonDown(0))
        {
            isJump = true;
        }
        else if (transform.position.y <= startPosition.y)
        {
            isJump = false;
            isTop = false;
            transform.position = startPosition;
        }
        if (isJump)
        {
            if (transform.position.y <= jumpH - 0.1f && !isTop)
            {
                transform.position = Vector2.Lerp(transform.position, new Vector2(transform.position.x, jumpH), jumpSpeed * Time.deltaTime);
            }
            else
            {
                isTop = true;
            }
            if (transform.position.y > startPosition.y && isTop)
            {
                transform.position = Vector2.MoveTowards(transform.position, startPosition, jumpSpeed * Time.deltaTime);
            }
        }
    }

    void GameOver()
    {
        Time.timeScale = 0;
        backsound.PlayOneShot(oversound);
        SceneManager.LoadScene("GameOver");
    }


    void OnTriggerEnter2D(Collider2D other)
    {
        if (other.gameObject.tag == "Obstacle")
        {
            //Debug.Log("충돌함");
            Time.timeScale = 0;
            backsound.PlayOneShot(oversound);
            SceneManager.LoadScene("GameOver");
        }

        if (other.gameObject.tag == "Coin")
        {
            Debug.Log("충돌함");
            other.gameObject.SetActive(false);
            ScoreUp(10);
            backsound.PlayOneShot(coinsound);
        }
    }
    public void ScoreUp(int scoreValue)
    {
        score += scoreValue;
        
        scoreText.text = "획득 금액 : " + score + "원";

        PlayerPrefs.SetInt("TotalScore", score);
    }
}