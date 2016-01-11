package com.example.chenweida.laddddgreendaodemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.greendao.Game;
import com.example.greendao.GameDao;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import de.greenrobot.dao.query.Query;

public class MainActivity extends AppCompatActivity {

    private Cursor cursor;

    @Bind(R.id.add_level) EditText addLevel;
    @Bind(R.id.add_point) EditText addPoint;
    @Bind(R.id.add) Button add;
    @Bind(R.id.search_level) EditText searchLevel;
    @Bind(R.id.search) Button search;
    @Bind(R.id.average) TextView average;

    @OnClick(R.id.add)
    void addPoint(View view)
    {
        Game game = new Game(null, Integer.parseInt(addLevel.getText().toString()),
                Integer.parseInt(addPoint.getText().toString()));
        getGameDao().insert(game);
    }

    @OnClick(R.id.search)
    void searchAverage(View view)
    {
        Query query = getGameDao().queryBuilder()
                .where(GameDao.Properties.Level.eq(Integer.parseInt(searchLevel.getText().toString()))).build();
        List<Game> games = query.list();
        int sum = 0;
        for (Game game : games)
        {
            sum += game.getPoint();
        }
        float averagePoint = (float)sum/games.size();
        average.setText("average: " + averagePoint);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        cursor = getDb().query(getGameDao().getTablename(),
                getGameDao().getAllColumns(), null, null, null, null, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private GameDao getGameDao() {
        // 通过 BaseApplication 类提供的 getDaoSession() 获取具体 Dao
        return ((MyApplication) this.getApplicationContext()).getDaoSession().getGameDao();
    }

    private SQLiteDatabase getDb() {
        // 通过 BaseApplication 类提供的 getDb() 获取具体 db
        return ((MyApplication) this.getApplicationContext()).getDb();
    }
}
