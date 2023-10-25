using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class obstacleBase : MonoBehaviour
{
    public float mobSpeed = 0;
    private Vector2 startPosition = new Vector2(28f, -2f); // Set the initial position here
    private float destroyXPosition = -10f; // Set the X position below which the object should be destroyed

    private void OnEnable()
    {
        transform.position = startPosition;
    }

    void Update()
    {
        transform.Translate(Vector2.left * Time.deltaTime * mobSpeed);

        if (transform.position.x < destroyXPosition)
        {
            gameObject.SetActive(false);
        }
    }
}