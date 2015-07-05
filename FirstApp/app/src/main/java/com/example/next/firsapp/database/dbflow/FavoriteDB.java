package com.example.next.firsapp.database.dbflow;

import android.content.Context;
import android.database.Cursor;

import com.example.next.firsapp.model.Favorite;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;

/**
 * Created by movile on 04/07/15.
 */
public class FavoriteDB {
    private Context mContext;

    public FavoriteDB(Context context){
        mContext = context;
    }

    public void save(Favorite favorite){
        FavoriteEntitydb entity = new FavoriteEntitydb(favorite.slug(), favorite.title());
        entity.save();
    }

    public Cursor all(){
        Cursor cursor = new Select().from(FavoriteEntitydb.class).queryCursorList().getCursor();
        return cursor;
    }

    public Favorite query(String slug) {
        Favorite favorite = null;
        FavoriteEntitydb entity = new Select()
                .from(FavoriteEntitydb.class)
                .where(Condition.column(FavoriteEntitydb$Table.SLUG).eq(slug))
                .querySingle();

        if (entity !=  null) {
            favorite = new Favorite(entity.slug, entity.title);
        }
        return favorite;
    }

    public void delete (String slug){
        new Delete()
                .from(FavoriteEntitydb.class)
                .where(Condition.column(FavoriteEntitydb$Table.SLUG).eq(slug))
                .queryClose();
    }
}
