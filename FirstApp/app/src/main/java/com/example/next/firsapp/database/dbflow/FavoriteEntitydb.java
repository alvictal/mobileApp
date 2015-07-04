package com.example.next.firsapp.database.dbflow;

import android.provider.BaseColumns;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by movile on 04/07/15.
 */
@Table(databaseName = SeriesTrackerDatabase.NAME)
public class FavoriteEntitydb extends BaseModel {
    @Column(name = BaseColumns._ID)
    @PrimaryKey(autoincrement = true)
    Long id;

    @Column
    String slug;

    @Column
    String title;

    public FavoriteEntitydb() {
    }

    public FavoriteEntitydb(String slug, String title) {
        this.slug = slug;
        this.title = title;
    }

    public String slug() {
        return slug;
    }

    public String title() {
        return title;
    }
}

