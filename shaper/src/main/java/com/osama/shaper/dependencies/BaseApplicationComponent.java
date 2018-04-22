package com.osama.shaper.dependencies;

import com.squareup.picasso.Picasso;

import dagger.Component;

@BaseApplicationScope
@Component(modules = {ServiceModule.class, PicassoModule.class, ActivityModule.class})
public interface BaseApplicationComponent {

    Picasso getPicasso();

    Object getService();
}
