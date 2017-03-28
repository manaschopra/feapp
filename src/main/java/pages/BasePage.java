package pages;

import utils.Helper;

abstract class BasePage {

	protected Helper helper = new Helper();

	public abstract void isValid();
}