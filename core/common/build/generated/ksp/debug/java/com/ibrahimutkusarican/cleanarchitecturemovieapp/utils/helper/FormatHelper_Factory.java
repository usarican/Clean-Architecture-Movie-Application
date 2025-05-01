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
public final class FormatHelper_Factory implements Factory<FormatHelper> {
  @Override
  public FormatHelper get() {
    return newInstance();
  }

  public static FormatHelper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FormatHelper newInstance() {
    return new FormatHelper();
  }

  private static final class InstanceHolder {
    private static final FormatHelper_Factory INSTANCE = new FormatHelper_Factory();
  }
}
