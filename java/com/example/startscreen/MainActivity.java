package com.example.startscreen;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences ;
    int matchesPlayed ;
    int matchesWon;
    int matchesLost;
    ListView black_list,white_list;
    List blackList,whiteList;
    Drawable pawn_white;
    private CountDownTimer whiteTimer;
    public CountDownTimer mainTimer;
    private CountDownTimer blackTimer;
    private long whitePausedTime = 0;
    Drawable btnbg;
    private long blackPausedTime = 0;
    String username="";
    int gamestart=0;
    private boolean isWhiteTimerRunning = false;
    private boolean isBlackTimerRunning = false;
    private long whiteRemainingTime = 15000;
    private long blackRemainingTime = 15000;
    private TextView whiteTimerTextView;
    private TextView blackTimerTextView;
    private boolean isWhiteTurn = true;
    TextView whitelabel,blacklabel;
    ImageView exit,pause,restart;
    DataBaseHandler db;
    SQLiteDatabase writableDB;
    Button a1,a2,a3,a4,a5,a6,a7,a8,b1,b2,b3,b4,b5,b6,b7,b8,c1,c2,c3,c4,c5,c6,c7,c8,d1,d2,d3,d4,d5,d6,d7,d8,e1,e2,e3,e4,e5,e6,e7,e8,f1,f2,f3,f4,f5,f6,f7,f8,g1,g2,g3,g4,g5,g6,g7,g8,h1,h2,h3,h4,h5,h6,h7,h8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = getSharedPreferences("username", Context.MODE_PRIVATE);
        username=sharedPref.getString("username","");
       // Log.d("shareduname",username+"\t");


        db=new DataBaseHandler(MainActivity.this);
        writableDB = db.getWritableDatabase();
        matchesPlayed=db.getMatchesPlayedForUser(username);
        matchesWon=db.getMatchesWonForUser(username);
        matchesLost=db.getMatchesLostForUser(username);

        matchesPlayed++;

        a1 = findViewById(R.id.a1);a2 = findViewById(R.id.a2);a3 = findViewById(R.id.a3);a4 = findViewById(R.id.a4);
        a5 = findViewById(R.id.a5);a6 = findViewById(R.id.a6);a7 = findViewById(R.id.a7);a8 = findViewById(R.id.a8);
        b1 = findViewById(R.id.b1);b2 = findViewById(R.id.b2);b3 = findViewById(R.id.b3);b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);b6 = findViewById(R.id.b6);b7 = findViewById(R.id.b7);b8 = findViewById(R.id.b8);
        c1 = findViewById(R.id.c1);c2 = findViewById(R.id.c2);c3 = findViewById(R.id.c3);c4 = findViewById(R.id.c4);
        c5 = findViewById(R.id.c5);c6 = findViewById(R.id.c6);c7 = findViewById(R.id.c7);c8 = findViewById(R.id.c8);
        d1 = findViewById(R.id.d1);d2 = findViewById(R.id.d2);d3 = findViewById(R.id.d3);d4 = findViewById(R.id.d4);
        d5 = findViewById(R.id.d5);d6 = findViewById(R.id.d6);d7 = findViewById(R.id.d7);d8 = findViewById(R.id.d8);
        e1 = findViewById(R.id.e1);e2 = findViewById(R.id.e2);e3 = findViewById(R.id.e3);e4 = findViewById(R.id.e4);
        e5 = findViewById(R.id.e5);e6 = findViewById(R.id.e6);e7 = findViewById(R.id.e7);e8 = findViewById(R.id.e8);
        f1 = findViewById(R.id.f1);f2 = findViewById(R.id.f2);f3 = findViewById(R.id.f3);f4 = findViewById(R.id.f4);
        f5 = findViewById(R.id.f5);f6 = findViewById(R.id.f6);f7 = findViewById(R.id.f7);f8 = findViewById(R.id.f8);
        g1 = findViewById(R.id.g1);g2 = findViewById(R.id.g2);g3 = findViewById(R.id.g3);g4 = findViewById(R.id.g4);
        g5 = findViewById(R.id.g5);g6 = findViewById(R.id.g6);g7 = findViewById(R.id.g7);g8 = findViewById(R.id.g8);
        h1 = findViewById(R.id.h1);h2 = findViewById(R.id.h2);h3 = findViewById(R.id.h3);h4 = findViewById(R.id.h4);
        h5 = findViewById(R.id.h5);h6 = findViewById(R.id.h6);h7 = findViewById(R.id.h7);h8 = findViewById(R.id.h8);

        placePawns();//bind

        a1.setTag("black");a2.setTag("black");a3.setTag("null");a4.setTag("null");
        a5.setTag("null");a6.setTag("null");a7.setTag("white");a8.setTag("white");
        b1.setTag("black");b2.setTag("black");b3.setTag("null");b4.setTag("null");
        b5.setTag("null");b6.setTag("null");b7.setTag("white");b8.setTag("white");
        c1.setTag("black");c2.setTag("black");c3.setTag("null");c4.setTag("null");
        c5.setTag("null");c6.setTag("null");c7.setTag("white");c8.setTag("white");
        d1.setTag("black");d2.setTag("black");d3.setTag("null");d4.setTag("null");
        d5.setTag("null");d6.setTag("null");d7.setTag("white");d8.setTag("white");
        e1.setTag("kingblack");e2.setTag("black");e3.setTag("null");e4.setTag("null");
        e5.setTag("null");e6.setTag("null");e7.setTag("white");e8.setTag("kingwhite");
        f1.setTag("black");f2.setTag("black");f3.setTag("null");f4.setTag("null");
        f5.setTag("null");f6.setTag("null");f7.setTag("white");f8.setTag("white");
        g1.setTag("black");g2.setTag("black");g3.setTag("null");g4.setTag("null");
        g5.setTag("null");g6.setTag("null");g7.setTag("white");g8.setTag("white");
        h1.setTag("black");h2.setTag("black");h3.setTag("null");h4.setTag("null");
        h5.setTag("null");h6.setTag("null");h7.setTag("white");h8.setTag("white");



        whitelabel = findViewById(R.id.white);
        blacklabel = findViewById(R.id.black);

        exit = findViewById(R.id.exitImageView);
        pause = findViewById(R.id.pauseImageView);
        restart = findViewById(R.id.restartImageView);

        whiteTimerTextView = findViewById(R.id.timer1);
        blackTimerTextView = findViewById(R.id.timer2);

        whiteTimer = createTimer(whiteRemainingTime, whiteTimerTextView);
        blackTimer = createTimer(blackRemainingTime, blackTimerTextView);

        whiteTimer.start();
        //blackTimer.start();

        blackList=new ArrayList();
        whiteList=new ArrayList();

        ArrayAdapter<String> blackAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, blackList);
        ArrayAdapter<String> whiteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, whiteList);

        black_list = findViewById(R.id.listblack);
        white_list = findViewById(R.id.listwhite);

        black_list.setAdapter(blackAdapter);
        white_list.setAdapter(whiteAdapter);


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showExitConfirmationDialog();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    whiteTimer.cancel();
                    isWhiteTimerRunning = false;
                } else {
                    blackTimer.cancel();
                    isBlackTimerRunning = false;
                }
                showPauseDialog();
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 showRestartConfirmationDialog();
            }
        });

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                 //   Toast.makeText(MainActivity.this,"End of the board!",Toast.LENGTH_SHORT).show();
                    whiteList.add(0,"Invalid Move!");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(a1,a2);
                    tick2();
                    blackList.add(0,"a1-->a2");
                    blackAdapter.notifyDataSetChanged();

                }
            }
        });
        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(a2,a1);
                    tick1();
                    whiteList.add(0,"a2-->a1");
                    whiteAdapter.notifyDataSetChanged();
                } else {
                    move(a2,a3);
                    tick2();
                     blackList.add(0,"a2-->a3");
                    blackAdapter.notifyDataSetChanged();

                }
            }
        });
        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(a3,a2);
                    whiteList.add(0,"a3-->a2");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(a3,a4);
                    blackList.add(0,"a3-->a4");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(a4,a3);
                    whiteList.add(0,"a4-->a3");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(a4,a5);
                    blackList.add(0,"a4-->a5");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(a5,a4);
                    whiteList.add(0,"a5-->a4");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(a5,a6);
                    blackList.add(0,"a5-->a6");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        a6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(a6,a5);
                    whiteList.add(0,"a6-->a5");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(a6,a7);
                    blackList.add(0,"a6-->a7");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        a7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(a7,a6);
                    whiteList.add(0,"a7-->a6");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(a7,a8);
                    blackList.add(0,"a7-->a8");
                    blackAdapter.notifyDataSetChanged();
                    tick2();
                }
            }
        });
        a8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(a8,a7);
                    whiteList.add(0,"a8-->a7");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                  //  Toast.makeText(MainActivity.this,"End of the board!",Toast.LENGTH_SHORT).show();
                    blackList.add(0,"Invalid Move!");
                    blackAdapter.notifyDataSetChanged();
                    tick2();
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                 //   Toast.makeText(MainActivity.this,"End of the board!",Toast.LENGTH_SHORT).show();
                    whiteList.add(0,"Invalid Move!");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();

                } else {
                    move(b1,b2);
                    blackList.add(0,"b1-->b2");
                    blackAdapter.notifyDataSetChanged();
                    tick2();
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(b2,b1);
                    whiteList.add(0,"b2-->b1");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(b2,b3);
                    blackList.add(0,"b2-->b3");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(b3,b2);
                    whiteList.add(0,"b3-->b2");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(b3,b4);
                    blackList.add(0,"b3-->b4");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(b4,b3);
                    whiteList.add(0,"b4-->b3");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(b4,b5);
                    blackList.add(0,"b4-->b5");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(b5,b4);
                    whiteList.add(0,"b5-->b4");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(b5,b6);
                    blackList.add(0,"b5-->b6");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(b6,b5);
                    whiteList.add(0,"b6-->b5");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(b6,b7);
                    blackList.add(0,"b6-->b7");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(b7,b6);
                    whiteList.add(0,"b7-->b6");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(b7,b8);
                    blackList.add(0,"b7-->b8");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(b8,b7);
                    whiteList.add(0,"b8-->b7");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
               //     Toast.makeText(MainActivity.this,"End of the board!",Toast.LENGTH_SHORT).show();
                    blackList.add(0,"Invalid Move!");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                //    Toast.makeText(MainActivity.this,"End of the board!",Toast.LENGTH_SHORT).show();
                    whiteList.add(0,"Invalid Move!");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();

                } else {
                    move(c1,c2);
                    blackList.add(0,"c1-->c2");
                    blackAdapter.notifyDataSetChanged();
                    tick2();
                }
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(c2,c1);
                    whiteList.add(0,"c2-->c1");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(c2,c3);
                    blackList.add(0,"c2-->c3");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(c3,c2);
                    whiteList.add(0,"c3-->c2");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(c3,c4);
                    blackList.add(0,"c3-->c4");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(c4,c3);
                    whiteList.add(0,"c4-->c3");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(c4,c5);
                    blackList.add(0,"c4-->c5");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(c5,c4);
                    whiteList.add(0,"c5-->c4");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(c5,c6);
                    blackList.add(0,"c5-->c6");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(c6,c5);
                    whiteList.add(0,"c6-->c5");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(c6,c7);
                    blackList.add(0,"c6-->c7");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        c7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(c7,c6);
                    whiteList.add(0,"c7-->c6");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(c7,c8);
                    blackList.add(0,"c7-->c8");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        c8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(c8,c7);
                    whiteList.add(0,"c8-->c7");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                   // Toast.makeText(MainActivity.this,"End of the board!",Toast.LENGTH_SHORT).show();
                    blackList.add(0,"Invalid Move!");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                 //   Toast.makeText(MainActivity.this,"End of the board!",Toast.LENGTH_SHORT).show();
                    whiteList.add(0,"Invalid Move!");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();

                } else {
                    move(d1,d2);
                    blackList.add(0,"d1-->d2");
                    blackAdapter.notifyDataSetChanged();
                    tick2();
                }
            }
        });
        d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(d2,d1);
                    whiteList.add(0,"d2-->d1");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(d2,d3);
                    blackList.add(0,"d2-->d3");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        d3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(d3,d2);
                    whiteList.add(0,"d3-->d2");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(d3,d4);
                    blackList.add(0,"d3-->d4");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        d4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(d4,d3);
                    whiteList.add(0,"d4-->d3");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(d4,d5);
                    blackList.add(0,"d4-->d5");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        d5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(d5,d4);
                    whiteList.add(0,"d5-->d4");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(d5,d6);
                    blackList.add(0,"d5-->d6");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        d6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(d6,d5);
                    whiteList.add(0,"d6-->d5");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(d6,d7);
                    blackList.add(0,"d6-->d7");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        d7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(d7,d6);
                    whiteList.add(0,"d7-->d6");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(d7,d8);
                    blackList.add(0,"d7-->d8");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        d8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(d8,d7);
                    whiteList.add(0,"d8-->d7");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                 //   Toast.makeText(MainActivity.this,"End of the board!",Toast.LENGTH_SHORT).show();
                    blackList.add(0,"Invalid Move!");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                  //  Toast.makeText(MainActivity.this,"End of the board!",Toast.LENGTH_SHORT).show();
                    whiteList.add(0,"Invalid Move!");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();

                } else {
                    move(e1,e2);
                    blackList.add(0,"e1-->e2");
                    blackAdapter.notifyDataSetChanged();
                    tick2();
                }
            }
        });
        e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(e2,e1);
                    whiteList.add(0,"e2-->e1");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(e2,e3);
                    blackList.add(0,"e2-->e3");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        e3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(e3,e2);
                    whiteList.add(0,"e3-->e2");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(e3,e4);
                    blackList.add(0,"e3-->e4");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        e4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(e4,e3);
                    whiteList.add(0,"e4-->e3");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(e4,e5);
                    blackList.add(0,"e4-->e5");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        e5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(e5,e4);
                    whiteList.add(0,"e5-->e4");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(e5,e6);
                    blackList.add(0,"e5-->e6");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        e6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(e6,e5);
                    whiteList.add(0,"e6-->e5");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(e6,e7);
                    blackList.add(0,"e6-->e7");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        e7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(e7,e6);
                    whiteList.add(0,"e7-->e6");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(e7,e8);
                    blackList.add(0,"e7-->e8");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        e8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(e8,e7);
                    whiteList.add(0,"e8-->e7");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                   // Toast.makeText(MainActivity.this,"End of the board!",Toast.LENGTH_SHORT).show();
                    blackList.add(0,"Invalid Move!");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                  //  Toast.makeText(MainActivity.this,"End of the board!",Toast.LENGTH_SHORT).show();
                    whiteList.add(0,"Invalid Move!");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();

                } else {
                    move(f1,f2);
                    blackList.add(0,"f1-->f2");
                    blackAdapter.notifyDataSetChanged();
                    tick2();
                }
            }
        });
        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(f2,f1);
                    whiteList.add(0,"f2-->f1");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(f2,f3);
                    blackList.add(0,"f2-->f3");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(f3,f2);
                    whiteList.add(0,"f3-->f2");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(f3,f4);
                    blackList.add(0,"f3-->f4");
                    blackAdapter.notifyDataSetChanged();
                    tick2();
                }
            }
        });
        f4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(f4,f3);
                    whiteList.add(0,"f4-->f3");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(f4,f5);
                    blackList.add(0,"f4-->f5");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        f5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(f5,f4);
                    whiteList.add(0,"f5-->f4");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(f5,f6);
                    blackList.add(0,"f5-->f6");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        f6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(f6,f5);
                    whiteList.add(0,"f6-->f5");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(f6,f7);
                    blackList.add(0,"f6-->f7");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        f7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(f7,f6);
                    whiteList.add(0,"f7-->f6");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(f7,f8);
                    blackList.add(0,"f7-->f8");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        f8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(f8,f7);
                    whiteList.add(0,"f8-->f7");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    //Toast.makeText(MainActivity.this,"End of the board!",Toast.LENGTH_SHORT).show();
                    blackList.add(0,"Invalid Move!");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        g1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                //    Toast.makeText(MainActivity.this,"End of the board!",Toast.LENGTH_SHORT).show();
                    whiteList.add(0,"Invalid Move!");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();

                } else {
                    move(g1,g2);
                    blackList.add(0,"g1-->g2");
                    blackAdapter.notifyDataSetChanged();
                    tick2();
                }
            }
        });
        g2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(g2,g1);
                    whiteList.add(0,"g2-->g1");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(g2,g3);
                    blackList.add(0,"g2-->g3");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        g3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(g3,g2);
                    whiteList.add(0,"g3-->g2");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(g3,g4);
                    blackList.add(0,"g3-->g4");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        g4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(g4,g3);
                    whiteList.add(0,"g4-->g3");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(g4,g5);
                    blackList.add(0,"g4-->g5");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        g5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(g5,g4);
                    whiteList.add(0,"g5-->g4");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(g5,g6);
                    blackList.add(0,"g5-->g6");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        g6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(g6,g5);
                    whiteList.add(0,"g6-->g5");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(g6,g7);
                    blackList.add(0,"g6-->g7");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        g7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn ){
                    move(g7,g6);
                    whiteList.add(0,"g7-->g6");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(g7,g8);
                    blackList.add(0,"g7-->g8");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        g8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(g8,g7);
                    whiteList.add(0,"g8-->g7");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                   // Toast.makeText(MainActivity.this,"End of the board!",Toast.LENGTH_SHORT).show();
                    blackList.add(0,"Invalid Move!");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        h1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                   // Toast.makeText(MainActivity.this,"End of the board!",Toast.LENGTH_SHORT).show();
                    whiteList.add(0,"Invalid Move!");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();

                } else {
                    move(h1,h2);
                    blackList.add(0,"h1-->h2");
                    blackAdapter.notifyDataSetChanged();
                    tick2();
                }
            }
        });
        h2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(h2,h1);
                    whiteList.add(0,"h2-->h1");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(h2,h3);
                    blackList.add(0,"h2-->h3");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        h3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(h3,h2);
                    whiteList.add(0,"h3-->h2");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(h3,h4);
                    blackList.add(0,"h3-->h4");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        h4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(h4,h3);
                    whiteList.add(0,"h4-->h3");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(h4,h5);
                    blackList.add(0,"h4-->h5");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        h5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(h5,h4);
                    whiteList.add(0,"h5-->h4");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(h5,h6);
                    blackList.add(0,"h5-->h6");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        h6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(h6,h5);
                    whiteList.add(0,"h6-->h5");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(h6,h7);
                    blackList.add(0,"h6-->h7");
                    blackAdapter.notifyDataSetChanged();
                    tick2();

                }
            }
        });
        h7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(h7,h6);
                    whiteList.add(0,"h6-->h5");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                    move(h7,h8);
                    blackList.add(0,"h7-->h8");
                    blackAdapter.notifyDataSetChanged();
                    tick2();
                }
            }
        });
        h8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhiteTurn) {
                    move(h8,h7);
                    whiteList.add(0,"h8-->h7");
                    whiteAdapter.notifyDataSetChanged();
                    tick1();
                } else {
                 //   Toast.makeText(MainActivity.this,"End of the board!",Toast.LENGTH_SHORT).show();
                    blackList.add(0,"Invalid Move!");
                    blackAdapter.notifyDataSetChanged();
                    tick2();
                }
            }
        });
    }
    public void move(Button src,Button dest)
    {
            Drawable layer2=null;
            Button clickedButton = src;
            Button destinationButton = dest;
            Drawable clickedButtonDrawable = clickedButton.getBackground();
            Drawable destinationButtonDrawable = destinationButton.getBackground();
            if (destinationButtonDrawable instanceof LayerDrawable)
            {
                LayerDrawable layerDrawable = (LayerDrawable) destinationButtonDrawable;
                if (layerDrawable.getNumberOfLayers() >= 3) {
                    layer2 = layerDrawable.getDrawable(2);
                }
            }
            if (layer2!=null && dest.getTag()!=src.getTag())
            {
                if(dest.getTag()=="kingwhite")
                {
                    destinationButton.setBackground(btnbg);
                    Drawable imageView = clickedButton.getBackground();
                    destinationButton.setBackground(imageView);
                    Drawable[] layers = new Drawable[3];
                    layers[0] = new ColorDrawable(Color.BLACK);
                    layers[1] = btnbg;
                    layers[2] = null;
                    dest.setTag(src.getTag());
                    src.setTag("null");
                    LayerDrawable layerDrawable1 = new LayerDrawable(layers);
                    src.setBackground(layerDrawable1);
                    matchesLost++;
                    showMessage3();
                }
                else if(dest.getTag()=="kingblack")
                {
                    destinationButton.setBackground(btnbg);
                    Drawable imageView = clickedButton.getBackground();
                    destinationButton.setBackground(imageView);
                    Drawable[] layers = new Drawable[3];
                    layers[0] = new ColorDrawable(Color.BLACK);
                    layers[1] = btnbg;
                    layers[2] = null;
                    dest.setTag(src.getTag());
                    src.setTag("null");
                    LayerDrawable layerDrawable1 = new LayerDrawable(layers);
                    src.setBackground(layerDrawable1);
                    matchesWon++;
                    showMessage1();
                }
                else {
                    destinationButton.setBackground(btnbg);
                    Drawable imageView = clickedButton.getBackground();
                    destinationButton.setBackground(imageView);
                    Drawable[] layers = new Drawable[3];
                    layers[0] = new ColorDrawable(Color.BLACK);
                    layers[1] = btnbg;
                    layers[2] = null;
                    dest.setTag(src.getTag());
                    src.setTag("null");
                    LayerDrawable layerDrawable1 = new LayerDrawable(layers);
                    src.setBackground(layerDrawable1);
                }
            }
            else if(layer2==null && clickedButtonDrawable != null)
            {
                    if (clickedButtonDrawable instanceof Drawable)
                    {
                        dest.setTag(src.getTag());
                        src.setTag("null");
                        Drawable imageView = clickedButtonDrawable;
                        destinationButton.setBackground(imageView);
                        Drawable[] layers = new Drawable[3];
                        layers[0] = new ColorDrawable(Color.BLACK);
                        layers[1] = btnbg;
                        layers[2] = null;
                        LayerDrawable layerDrawable2 = new LayerDrawable(layers);
                        src.setBackground(layerDrawable2);
                    }
            }
            else
            {
                Toast.makeText(this, "Invalid Move!", Toast.LENGTH_SHORT).show();
            }
            db.updateMatchesPlayedForUser(username,matchesPlayed);
            db.updateMatchesWonForUser(username,matchesWon);
            db.updateMatchesLostForUser(username,matchesLost);
    }
    public void placePawns()
    {
        int[] buttonIds1 = {R.id.a1, R.id.b1, R.id.c1, R.id.d1, R.id.e1, R.id.f1, R.id.g1, R.id.h1};
        int[] imageResourceIds1 = {
                R.drawable.rook_black, R.drawable.knight_black, R.drawable.bishop_black,
                R.drawable.queen_black, R.drawable.king_black, R.drawable.bishop_black,
                R.drawable.knight_black, R.drawable.rook_black
        };
        for (int i = 0; i < buttonIds1.length; i++) {
            Button button = findViewById(buttonIds1[i]);
            Drawable buttonBackground = button.getBackground();
            Drawable imageViewDrawable = getResources().getDrawable(imageResourceIds1[i]);
            imageViewDrawable.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
            Drawable[] layers = new Drawable[3];
            layers[0] = new ColorDrawable(Color.BLACK);
            layers[1] = buttonBackground;
            layers[2] = imageViewDrawable;
            LayerDrawable layerDrawable = new LayerDrawable(layers);
            button.setBackground(layerDrawable);
        }
        int[] buttonIds2 = {R.id.a2, R.id.b2, R.id.c2, R.id.d2, R.id.e2, R.id.f2, R.id.g2, R.id.h2};
        for (int j : buttonIds2) {
            Button button = findViewById(j);
            Drawable buttonBackground = button.getBackground();
            Drawable imageViewDrawable = getResources().getDrawable(R.drawable.pawn_black);
            btnbg = buttonBackground;
            Drawable[] layers = new Drawable[3];
            layers[0] = new ColorDrawable(Color.BLACK);
            layers[1] = buttonBackground;
            layers[2] = imageViewDrawable;
            LayerDrawable layerDrawable = new LayerDrawable(layers);
            button.setBackground(layerDrawable);
        }
        int[] buttonIds3 = {R.id.a8, R.id.b8, R.id.c8, R.id.d8, R.id.e8, R.id.f8, R.id.g8, R.id.h8};
        int[] imageResourceIds3 = {
                R.drawable.rook_white, R.drawable.knight_white, R.drawable.bishop_white,
                R.drawable.queen_white, R.drawable.king_white,  R.drawable.rook_white,
                R.drawable.knight_white, R.drawable.bishop_white
        };
        for (int i = 0; i < buttonIds3.length; i++) {
            Button button = findViewById(buttonIds3[i]);
            Drawable buttonBackground = button.getBackground();
            Drawable imageViewDrawable = getResources().getDrawable(imageResourceIds3[i]);
            Drawable[] layers = new Drawable[3];
            layers[0] = new ColorDrawable(Color.BLACK);
            layers[1] = buttonBackground;
            layers[2] = imageViewDrawable;;
            LayerDrawable layerDrawable = new LayerDrawable(layers);
            button.setBackground(layerDrawable);
        }
        int[] buttonIds4 = {R.id.a7, R.id.b7, R.id.c7, R.id.d7, R.id.e7, R.id.f7, R.id.g7, R.id.h7};
        for (int j : buttonIds4) {
            Button button = findViewById(j);
            Drawable buttonBackground = button.getBackground();
            Drawable imageViewDrawable = getResources().getDrawable(R.drawable.pawn_white);
            Drawable[] layers = new Drawable[3];
            layers[0] = new ColorDrawable(Color.BLACK);
            layers[1] = buttonBackground;
            layers[2] = imageViewDrawable;
            LayerDrawable layerDrawable = new LayerDrawable(layers);
            imageViewDrawable = layerDrawable.getDrawable(2);
            pawn_white = imageViewDrawable;
            button.setBackground(layerDrawable);
        }
    }
    public void tick1()
    {
        btndisable2();
        whiteTimer.cancel();
        blackTimer.start();
        whiteTimerTextView.setText("00:15");
        isWhiteTurn=false;
    }
    public  void tick2()
    {
        btndisable1();
        blackTimer.cancel();
        whiteTimer.start();
        blackTimerTextView.setText("00:15");
        isWhiteTurn=true;
    }
    private void resumeTimer() {
        if (isWhiteTurn) {
            startWhiteTimer(whitePausedTime);
        } else {
            startBlackTimer(blackPausedTime);
        }
    }
    private void startWhiteTimer(long durationMillis) {
        whiteTimer = createTimer(whitePausedTime, whiteTimerTextView);
        whiteTimer.start();
        isWhiteTimerRunning = true;
    }
    private void startBlackTimer(long durationMillis) {
        blackTimer = createTimer(blackPausedTime, blackTimerTextView);
        blackTimer.start();
        isBlackTimerRunning = true;
    }
    private CountDownTimer createTimer(long millisInFuture, final TextView textView) {
        return new CountDownTimer(millisInFuture, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(isWhiteTurn)
                {
                    btndisable1();
                    blacklabel.setBackgroundColor(Color.parseColor("#000000"));
                    whitelabel.setBackgroundColor(Color.parseColor("#5DF663"));
                    whitelabel.setTextColor(Color.parseColor("#000000"));
                    blacklabel.setTextColor(Color.parseColor("#FFFFFF"));
                    whitePausedTime = millisUntilFinished;
                }
                else
                {
                    btndisable2();
                    blacklabel.setTextColor(Color.parseColor("#000000"));
                    whitelabel.setBackgroundColor(Color.parseColor("#000000"));
                    whitelabel.setTextColor(Color.parseColor("#FFFFFF"));
                    blacklabel.setBackgroundColor(Color.parseColor("#5DF663"));
                    blackPausedTime=millisUntilFinished;
                }

                long seconds = (millisUntilFinished / 1000) % 60;
                textView.setText(String.format("00:%02d", seconds));
            }
            @Override
            public void onFinish() {
                textView.setText("00:00");
                switchPlayerTurn();
            }
        };
    }
    public void btndisable1()
    {
        Button[] allButtons = new Button[]{
                a1, a2, a3, a4, a5, a6, a7, a8,
                b1, b2, b3, b4, b5, b6, b7, b8,
                c1, c2, c3, c4, c5, c6, c7, c8,
                d1, d2, d3, d4, d5, d6, d7, d8,
                e1, e2, e3, e4, e5, e6, e7, e8,
                f1, f2, f3, f4, f5, f6, f7, f8,
                g1, g2, g3, g4, g5, g6, g7, g8,
                h1, h2, h3, h4, h5, h6, h7, h8
        };
        for(Button button :allButtons)
        {
            if (button.getTag() != null && button.getTag().toString().equals("null")) {
                button.setClickable(false);
            } else {
                button.setClickable(true);
            }
        }
        for (Button button : allButtons) {
                if (button.getTag() != null && button.getTag().toString().contains("black")) {
                    button.setClickable(false);
                } else {
                    button.setClickable(true);
                }
            }
    }
    public void btndisable2()
    {
        Button[] allButtons = new Button[]{
                a1, a2, a3, a4, a5, a6, a7, a8,
                b1, b2, b3, b4, b5, b6, b7, b8,
                c1, c2, c3, c4, c5, c6, c7, c8,
                d1, d2, d3, d4, d5, d6, d7, d8,
                e1, e2, e3, e4, e5, e6, e7, e8,
                f1, f2, f3, f4, f5, f6, f7, f8,
                g1, g2, g3, g4, g5, g6, g7, g8,
                h1, h2, h3, h4, h5, h6, h7, h8
        };
        for(Button button :allButtons)
        {
            if (button.getTag() != null && button.getTag().toString().equals("null")) {
                button.setClickable(false);
            } else {
                button.setClickable(true);
            }
        }
        for (Button button : allButtons) {
            if (button.getTag() != null && button.getTag().toString().contains("white")) {
                button.setClickable(false);
            }
            else
            {
                button.setClickable(true);
            }
        }
    }
    private void switchPlayerTurn()
    {
        if (isWhiteTurn) {
            whiteTimer.cancel();
            blackTimer.start();
        } else {

            blackTimer.cancel();
            whiteTimer.start();
        }
        isWhiteTurn = !isWhiteTurn;
    }
    private void showPauseDialog() {
        Dialog pause = new Dialog(this);
        pause.setContentView(R.layout.custon_pause_dialog);
        pause.show();
        ImageView pausevw = pause.findViewById(R.id.pausevw);
        pausevw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause.dismiss();
                resumeTimer();
            }
        });
    }
    private void showExitConfirmationDialog()
    {
        Dialog exitDialog = new Dialog(this);
        exitDialog.setContentView(R.layout.custom_exit_dialog);
        Button yesButton = exitDialog.findViewById(R.id.yesbutton);
        Button noButton = exitDialog.findViewById(R.id.nobutton);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               MainActivity.this.finish();
            }
        });
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitDialog.dismiss();
            }
        });
        exitDialog.show();
    }
    private void showMessage1()
    {
        Dialog msg=new Dialog(this);
        msg.setContentView(R.layout.activity_message1);
        msg.show();
        ImageView home=msg.findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
               Intent i=new Intent(MainActivity.this, home_page.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
                startActivity(i, options.toBundle());
            }
        });
    }
    private void showMessage3()
    {
        Dialog msg=new Dialog(this);
        msg.setContentView(R.layout.message3);
        msg.show();
        ImageView home=msg.findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i=new Intent(MainActivity.this, home_page.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
                startActivity(i, options.toBundle());
            }
        });
    }

    private void showRestartConfirmationDialog()
    {
        Dialog restartDialog = new Dialog(this);
        restartDialog.setContentView(R.layout.custom_restart_dialog);
        Button yesButton = restartDialog.findViewById(R.id.yesbutton);
        Button noButton = restartDialog.findViewById(R.id.nobutton);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(MainActivity.this, game_selection.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
                startActivity(i, options.toBundle());
            }
        });
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartDialog.dismiss();
            }
        });
        restartDialog.show();
    }
    }
