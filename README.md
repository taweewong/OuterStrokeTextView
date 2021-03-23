# OuterStrokeTextView

## Installation

If your project don't has Jitpack, you have to add it first

```gradle
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```

Then add dependencies

```gradle
dependencies {
    implementation 'com.github.taweewong:OuterStrokeTextView:1.0'
}
```

## Usage

This library provides 2 attributes

```
app:outlineColor
app:outlineWidth
```

*Example*
```xml
<com.taweewong.textview.OuterStrokeTextView
    android:id="@+id/textView"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Hello World!"
    android:textSize="18sp"
    app:outlineColor="#67C5F8"
    app:outlineWidth="1dp" />
```

*Result*

<img width="381" alt="image" src="https://user-images.githubusercontent.com/15921410/112095704-8d4c2180-8bcf-11eb-96ab-0fc036099ed0.png">
