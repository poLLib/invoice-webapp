import React from "react";

export function FlashMessage({ theme, text }) {
  return <div className={"alert alert-" + theme}>{text}</div>;
}

export default FlashMessage;

//TODO: uprava faktury - pridat flash msg, treba po prenacteni na jinou url
// vypsani statistik
// udelat hezke rozlozeni ve 2 sloupcich v personDetail