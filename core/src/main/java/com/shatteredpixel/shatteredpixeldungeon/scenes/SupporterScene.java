/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2019-2024 Evan Debenham
 *
 * Experienced Pixel Dungeon
 * Copyright (C) 2019-2024 Trashbox Bobylev
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.shatteredpixeldungeon.scenes;

import com.shatteredpixel.shatteredpixeldungeon.Chrome;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.Archs;
import com.shatteredpixel.shatteredpixeldungeon.ui.ExitButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.StyledButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.shatteredpixel.shatteredpixeldungeon.windows.IconTitle;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Image;
import com.watabou.noosa.NinePatch;
import com.watabou.noosa.ui.Component;

public class SupporterScene extends PixelScene {

	private static final int BTN_HEIGHT = 22;
	private static final int GAP = 2;

	@Override
	public void create() {
		super.create();

		uiCamera.visible = false;

		int w = Camera.main.width;
		int h = Camera.main.height;

		int elementWidth = PixelScene.landscape() ? 202 : 120;

		Archs archs = new Archs();
		archs.setSize(w, h);
		add(archs);

		ExitButton btnExit = new ExitButton();
		btnExit.setPos(w - btnExit.width(), 0);
		add(btnExit);

		IconTitle title = new IconTitle(Icons.GOLD.get(), Messages.get(this, "title"));
		title.setSize(200, 0);
		title.setPos(
				(w - title.reqWidth()) / 2f,
				(20 - title.height()) / 2f
		);
		align(title);
		add(title);

		SupporterMessage msg = new SupporterMessage();
		msg.setSize(elementWidth, 0);
		add(msg);

		StyledButton link = new StyledButton(Chrome.Type.GREY_BUTTON_TR, Messages.get(this, "supporter_link")){
			@Override
			protected void onClick() {
				super.onClick();
				String link = "mailto:trashbox.bobylev@gmail.com";
				ShatteredPixelDungeon.platform.openURI(link);
			}
		};
		link.icon(Icons.get(Icons.NEWS));
		link.textColor(Window.TITLE_COLOR);
		link.setSize(elementWidth, BTN_HEIGHT);
		link.enable(false);
		add(link);

		StyledButton link2 = new StyledButton(Chrome.Type.GREY_BUTTON_TR, Messages.get(this, "issues_link")){
			@Override
			protected void onClick() {
				super.onClick();
				String link = "https://github.com/TrashboxBobylev/Experienced-Pixel-Dungeon-Redone/issues";
				ShatteredPixelDungeon.platform.openURI(link);
			}
		};
		link2.icon(Icons.get(Icons.CHANGES));
		link2.textColor(Window.TITLE_COLOR);
		link2.setSize(elementWidth, BTN_HEIGHT);
		link2.enable(false);
		add(link2);

		float elementHeight = msg.height() + BTN_HEIGHT + GAP;

		float top = 16 + (h - 16 - elementHeight)/2f;
		float left = (w-elementWidth)/2f;

		msg.setPos(left, top);
		align(msg);

		link.setPos(left, msg.bottom()+GAP);
		align(link);

		link2.setPos(left, link.bottom()+GAP);
		align(link2);

	}

	@Override
	protected void onBackPressed() {
		ShatteredPixelDungeon.switchNoFade( TitleScene.class );
	}

	private static class SupporterMessage extends Component {

		NinePatch bg;
		RenderedTextBlock text;
		Image icon;

		@Override
		protected void createChildren() {
			bg = Chrome.get(Chrome.Type.GREY_BUTTON_TR);
			add(bg);

			String message = Messages.get(Dungeon.class, "last_words");
			message += "\n\n- ae7th";

			text = PixelScene.renderTextBlock(message, 6);
			add(text);

			icon = Icons.get(Icons.BOBBY_IS_VERY_STRANGE_PERSON_BECAUSE_HE_TRIES_TO_REFERENCE_HIMSELF_IN_NEW_SHATTERED_CREDITS_SCREEN);
			add(icon);

		}

		@Override
		protected void layout() {
			bg.x = x;
			bg.y = y;

			text.maxWidth((int)width - bg.marginHor());
			text.setPos(x + bg.marginLeft(), y + bg.marginTop());

			icon.y = text.bottom() - icon.height() + 4;
			icon.x = text.right() + 10;

			height = (text.bottom() + 2) - y;

			height += bg.marginBottom();

			bg.size(width, height);

		}

	}

}
