package com.leohulabb.utils.picassoUtils;

import com.squareup.picasso.Picasso;

public class PicassoPauseOnScrollListener extends PauseOnScrollListener {

    public PicassoPauseOnScrollListener(boolean pauseOnScroll, boolean pauseOnFling) {
        super(pauseOnScroll, pauseOnFling);
    }

    @Override
    public void resume() {
        Picasso.with(getContext()).resumeTag(getContext());
    }

    @Override
    public void pause() {
        Picasso.with(getContext()).pauseTag(getContext());
    }
}
