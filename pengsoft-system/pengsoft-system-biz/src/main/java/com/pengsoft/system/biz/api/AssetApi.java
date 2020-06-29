package com.pengsoft.system.biz.api;

import com.pengsoft.support.biz.api.EntityApi;
import com.pengsoft.system.biz.facade.AssetFacade;
import com.pengsoft.system.biz.service.ObjectStorageService;
import com.pengsoft.system.domain.entity.Asset;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.util.List;

/**
 * The web api of {@link Asset}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/asset")
public class AssetApi extends EntityApi<AssetFacade, Asset, String> {

    @Inject
    private ObjectStorageService objectStorageService;

    @PostMapping("upload")
    public List<Asset> upload(@RequestParam("file") final List<MultipartFile> files,
                              @RequestParam(name = "locked", defaultValue = "false") final boolean locked) {
        return getFacade().save(objectStorageService.upload(files, locked));
    }

    @Override
    public void delete(final Predicate predicate) {
        final var assets = getFacade().findAll(predicate, Sort.unsorted());
        getFacade().delete(assets);
        objectStorageService.delete(assets);
    }

}
