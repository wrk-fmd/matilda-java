package at.wrk.fmd.matilda.pojo;

import org.springframework.data.domain.Sort;

public class CustomSort {

    public Sort sortByIdAsc() {
        return new Sort(Sort.Direction.ASC, "id");
    }
    
    public Sort sortByIdDesc() {
        return new Sort(Sort.Direction.DESC, "id");
    }    
}