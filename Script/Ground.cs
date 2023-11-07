using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Ground : MonoBehaviour
{
    public SpriteRenderer[] tiles;
    public Sprite[] ground;
    public float speed;
    private SpriteRenderer temp;
    private float tileWidth;

    void Start()
    {
        temp = tiles[0];
        tileWidth = tiles[0].bounds.size.x - 0.08f;

        for (int i = 0; i < tiles.Length; i++)
        {
            tiles[i].transform.position = new Vector2(i * tileWidth, -5.0f);
            tiles[i].sprite = ground[Random.Range(0, ground.Length)];
        }
    }

    void Update()
    {
            for (int i = 0; i < tiles.Length; i++)
            {
                tiles[i].transform.Translate(new Vector2(-1, 0) * Time.deltaTime * speed);

                if (tiles[i].transform.position.x < -tileWidth)
                {
                    float maxX = float.MinValue;

                    for (int j = 0; j < tiles.Length; j++)
                    {
                        if (tiles[j].transform.position.x > maxX)
                            maxX = tiles[j].transform.position.x;
                    }

                    tiles[i].transform.position = new Vector2(maxX + tileWidth, -5.0f);
                    tiles[i].sprite = ground[Random.Range(0, ground.Length)];
                }
            }
        }
    }