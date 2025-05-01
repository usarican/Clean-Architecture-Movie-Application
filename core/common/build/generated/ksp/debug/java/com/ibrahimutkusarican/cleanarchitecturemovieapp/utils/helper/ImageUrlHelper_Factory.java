package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.helper;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class ImageUrlHelper_Factory implements Factory<ImageUrlHelper> {
  @Override
  public ImageUrlHelper get() {
    return newInstance();
  }

  public static ImageUrlHelper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ImageUrlHelper newInstance() {
    return new ImageUrlHelper();
  }

  private static final class InstanceHolder {
    private static final ImageUrlHelper_Factory INSTANCE = new ImageUrlHelper_Factory();
  }
}
