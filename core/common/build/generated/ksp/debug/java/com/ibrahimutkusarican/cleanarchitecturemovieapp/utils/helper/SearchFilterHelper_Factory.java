package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.helper;

import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.StringProvider;
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
public final class SearchFilterHelper_Factory implements Factory<SearchFilterHelper> {
  private final Provider<StringProvider> stringProvider;

  public SearchFilterHelper_Factory(Provider<StringProvider> stringProvider) {
    this.stringProvider = stringProvider;
  }

  @Override
  public SearchFilterHelper get() {
    return newInstance(stringProvider.get());
  }

  public static SearchFilterHelper_Factory create(Provider<StringProvider> stringProvider) {
    return new SearchFilterHelper_Factory(stringProvider);
  }

  public static SearchFilterHelper newInstance(StringProvider stringProvider) {
    return new SearchFilterHelper(stringProvider);
  }
}
