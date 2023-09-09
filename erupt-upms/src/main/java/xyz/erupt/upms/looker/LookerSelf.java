package xyz.erupt.upms.looker;

import org.springframework.stereotype.Component;
import xyz.erupt.annotation.PreDataProxy;
import xyz.erupt.annotation.fun.DataProxy;
import xyz.erupt.annotation.query.Condition;
import xyz.erupt.upms.helper.HyperModelCreatorVo;
import xyz.erupt.upms.service.EruptUserService;

import javax.annotation.Resource;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.List;

/**
 * @author YuePeng
 * date 2021/3/10 11:30
 */
@MappedSuperclass
@PreDataProxy(LookerSelf.Comp.class)
public class LookerSelf extends HyperModelCreatorVo implements DataProxy<Void> {

    @Component
    static class Comp implements DataProxy<Void> {

        @Resource
        @Transient
        private EruptUserService eruptUserService;

        @Override
        public String beforeFetch(String modelName, List<Condition> conditions) {
            if (eruptUserService.getCurrentEruptUser().getIsAdmin()) return null;
            return modelName + ".createUser.id = " + eruptUserService.getCurrentUid();
        }

    }

}
