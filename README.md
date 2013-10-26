# autolayout-labels

Takes STDIN as input (well, \*in\*, I should say) and replaces all Obj-C
memory addresses, gives them friendly names, and prints out the output.

The purpose is to take output like:

```
Unable to simultaneously satisfy constraints:
( "<NSAutoresizingMaskLayoutConstraint:0x4011d8f60 h=-&- v=-&- H:|-(0)-[FlippedDocumentView:0x4011b76e0] (Names: '|':NSClipView:0x40120eb80 )>",
  "<NSLayoutConstraint:0x4012a5c80 H:|-(10)-[TextViewModuleView:0x401236e80] (Names: '|':FlippedDocumentView:0x4011b76e0 )>",
  "<NSLayoutConstraint:0x4011148e0 H:[TextViewModuleView:0x401236e80]-(10)-| (Names: '|':FlippedDocumentView:0x4011b76e0 )>",
  "<NSAutoresizingMaskLayoutConstraint:0x4011d8f00 h=-&- v=-&- H:[FlippedDocumentView:0x4011b76e0]-(0)-| (Names: '|':NSClipView:0x40120eb80 )>",
  "<NSAutoresizingMaskLayoutConstraint:0x4011d5e00 h=--& v=--& H:[NSClipView:0x40120eb80(0)]>" )
```

And turn it into slightly more friendly output like:

```
( "<NSAutoresizingMaskLayoutConstraint:0x4011d8f60 h=-&- v=-&- H:|-(0)-[FlippedDocumentView:Bob] (Names: '|':NSClipView:Fred )>",
  "<NSLayoutConstraint:0x4012a5c80 H:|-(10)-[TextViewModuleView:Bob] (Names: '|':FlippedDocumentView:Fred )>",
  "<NSLayoutConstraint:0x4011148e0 H:[TextViewModuleView:Bob]-(10)-| (Names: '|':FlippedDocumentView:Fred )>",
  "<NSAutoresizingMaskLayoutConstraint:0x4011d8f00 h=-&- v=-&- H:[FlippedDocumentView:Bob]-(0)-| (Names: '|':NSClipView:Fred )>",
  "<NSAutoresizingMaskLayoutConstraint:0x4011d5e00 h=--& v=--& H:[NSClipView:Bob(0)]>" )
```

## Usage

FIXME

## License

Copyright © 2013 FIXME

This project is placed into the public domain.
