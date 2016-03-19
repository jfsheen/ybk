/*
 * Copyright (c) 2009 Piotr Piastucki
 * 
 * This file is part of Patchca CAPTCHA library.
 * 
 *  Patchca is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  Patchca is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *  
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Patchca. If not, see <http://www.gnu.org/licenses/>.
 */
package cc.aisc.ybk.commons.utils.patchca.service;


import cc.aisc.ybk.commons.costant.Constant;
import cc.aisc.ybk.commons.utils.patchca.background.SingleColorBackgroundFactory;
import cc.aisc.ybk.commons.utils.patchca.color.SingleColorFactory;
import cc.aisc.ybk.commons.utils.patchca.filter.predefined.CurvesRippleFilterFactory;
import cc.aisc.ybk.commons.utils.patchca.font.RandomFontFactory;
import cc.aisc.ybk.commons.utils.patchca.text.renderer.BestFitTextRenderer;
import cc.aisc.ybk.commons.utils.patchca.word.AdaptiveRandomWordFactory;
import org.springframework.stereotype.Component;

@Component
public class ConfigurableCaptchaService extends AbstractCaptchaService {

	public ConfigurableCaptchaService() {
		backgroundFactory = new SingleColorBackgroundFactory();
		wordFactory = new AdaptiveRandomWordFactory();
		fontFactory = new RandomFontFactory();
		textRenderer = new BestFitTextRenderer();
		colorFactory = new SingleColorFactory();
		filterFactory = new CurvesRippleFilterFactory(colorFactory);
		textRenderer.setLeftMargin(Constant.CAPTCHA_MARGIN_LEFT);
		textRenderer.setRightMargin(Constant.CAPTCHA_MARGIN_RIGHT);
		width = Constant.CAPTCHA_IMG_WIDTH;
		height = Constant.CAPTCHA_IMG_HEIGHT;
	}

}
