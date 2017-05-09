package com.map.mapmaxv1.db;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.map.mapmaxv1.MapApp;
import com.map.mapmaxv1.dto.DaoSession;
import com.map.mapmaxv1.dto.MarkDTO;
import com.map.mapmaxv1.dto.MarkDTODao;

import java.util.Date;
import java.util.List;

public class MarkHelper extends AppCompatActivity
{

    private Date lastDate;
    private long minID;

    private MarkDTODao markDAO;

    public Date getLastDate() {
        return lastDate;
    }

    public MarkHelper(Context context) {
        super();

        DaoSession daoSession = ((MapApp)getApplication()).getDaoSession();
        markDAO = daoSession.getMarkDTODao();

        minID = 0;
        lastDate = null;
    }

    public void writeMark(MarkDTO markDTO,boolean global)
    {
        if(!global)
        {
            minID--;
            markDTO.setMarkId(minID);
        }
        markDAO.insert(markDTO);
    }

    public void writeListMark(List<MarkDTO> markDTOs)
    {
        for(MarkDTO mark : markDTOs)
        {
            markDAO.insert(mark);
        }
    }
    public void updateMark(MarkDTO markDTO)
    {
        markDAO.update(markDTO);
    }

    public void deleteMark(MarkDTO markDTO)
    {
        markDAO.delete(markDTO);
    }

    public List<MarkDTO>  readMark()
    {
        List<MarkDTO> markDTOs;

        markDTOs= markDAO.loadAll();

        for(MarkDTO mark : markDTOs)
        {
            if(mark.getMarkId()<minID) minID = mark.getMarkId();
        }

        return markDTOs;
    }
}
