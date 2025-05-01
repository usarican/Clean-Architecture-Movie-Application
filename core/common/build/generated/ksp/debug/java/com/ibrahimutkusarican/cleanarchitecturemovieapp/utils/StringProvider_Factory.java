package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class StringProvider_Factory implements Factory<StringProvider> {
  private final Provider<Context> contextProvider;

  public StringProvider_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public StringProvider get() {
    return newInstance(contextProvider.get());
  }

  public static StringProvider_Factory create(Provider<Context> contextProvider) {
    return new StringProvider_Factory(contextProvider);
  }

  public static StringProvider newInstance(Context context) {
    return new StringProvider(context);
  }
}
