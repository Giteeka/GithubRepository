package com.hkuaapp.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.hkuaapp.data.local.db.converter.FoodCategoryConverter;
import com.hkuaapp.data.local.db.converter.OptionConverter;
import com.hkuaapp.data.local.db.converter.QuestionConverter;
import com.hkuaapp.data.local.db.converter.TableConverter;
import com.hkuaapp.data.local.db.dao.DocumentCategoryDao;
import com.hkuaapp.data.local.db.dao.DocumentDao;
import com.hkuaapp.data.local.db.dao.EventDao;
import com.hkuaapp.data.local.db.dao.NursingCategoryDao;
import com.hkuaapp.data.local.db.dao.NursingDao;
import com.hkuaapp.data.local.db.dao.SliderDao;
import com.hkuaapp.data.local.db.dao.UserDao;
import com.hkuaapp.modal.document.DocumentCategory;
import com.hkuaapp.modal.events.EventInfo;
import com.hkuaapp.modal.home.Document;
import com.hkuaapp.modal.home.Slider;
import com.hkuaapp.modal.login.UserDetail;
import com.hkuaapp.modal.nursing.NursingCategory;
import com.hkuaapp.modal.nursing.NursingInfo;

@Database(entities = {UserDetail.class, EventInfo.class, Slider.class,
        Document.class, DocumentCategory.class, NursingCategory.class, NursingInfo.class},version = 1)
@TypeConverters({FoodCategoryConverter.class, QuestionConverter.class, OptionConverter.class,
        TableConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract EventDao eventDao();

    public abstract SliderDao sliderDao();

    public abstract DocumentDao documentDao();

    public abstract DocumentCategoryDao documentCategoryDao();

    public abstract NursingCategoryDao nursingCategoryDao();

    public abstract NursingDao nursingDao();
}
