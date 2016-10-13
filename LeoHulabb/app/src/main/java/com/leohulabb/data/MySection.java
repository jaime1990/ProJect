package com.leohulabb.data;


import com.commonui.listview.entity.SectionEntity;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MySection extends SectionEntity<UniversityListDto> {
    private boolean isMroe;
    public MySection(boolean isHeader, String header, boolean isMroe) {
        super(isHeader, header);
        this.isMroe = isMroe;
    }

    public MySection(UniversityListDto t) {
        super(t);
    }

    public boolean isMroe() {
        return isMroe;
    }

    public void setMroe(boolean mroe) {
        isMroe = mroe;
    }
}
