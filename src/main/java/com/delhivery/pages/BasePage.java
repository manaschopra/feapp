package com.delhivery.pages;

import com.delhivery.utils.Helper;

abstract class BasePage {

	protected Helper helper = new Helper();

	public abstract void isValid();
}