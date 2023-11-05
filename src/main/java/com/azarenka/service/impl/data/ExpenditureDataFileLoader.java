package com.azarenka.service.impl.data;

import com.azarenka.domain.ResourcePassword;
import com.azarenka.domain.finance.Expenditure;
import com.azarenka.exeptions.FileAppDataNotFoundException;
import com.azarenka.service.api.IFileDataManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.PostConstruct;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 03/20/2023
 *
 * @author Anton Azarenka
 */
@Service("app.resource.expenditure.data.manager")
public class ExpenditureDataFileLoader extends AbstractFileDataLoaderManager implements
    IFileDataManager<List<Expenditure>> {

    @Value(value = "${app.common.expenditure.filename}")
    private Resource resource;

    @PostConstruct
    public void init() {
        setResource(resource);
    }

    @Override
    public List<Expenditure> loadData() {
        List<Expenditure> properties = null;
        try {
            properties = (List<Expenditure>) loadFile();
        } catch (FileAppDataNotFoundException e) {
            properties = saveData(List.of());
        }
        return properties;
    }

    @Override
    public List<Expenditure> saveData(List<Expenditure> data) {
        return saveFile(data) ? data : null;
    }
}
